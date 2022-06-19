package com.finance.exchrates.db;

import com.finance.exchrates.model.CurrencyRates;
import com.finance.exchrates.model.ExchangeRates;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class ExchRatesDBHandler {

    protected NamedParameterJdbcTemplate ratesdbJdbcTemplate;

    @Autowired
    @DependsOn("ratesdbJdbcTemplate")
    public void setRatesdbJdbcTemplate(NamedParameterJdbcTemplate ratesdbJdbcTemplate) {
        this.ratesdbJdbcTemplate = ratesdbJdbcTemplate;
    }

    public ExchRatesDBHandler() {
        System.out.println("JDBC template : " + ratesdbJdbcTemplate);
    }


    public CurrencyRates findSpecificRatesByFromAndToAndDate(String fromCurr, String toCurr, String date) {
       String sql = "SELECT * FROM CURRENCY_RATES c WHERE c.FROM_CURR = :fromCurr and c.TO_CURR = :toCurr and DATE_FORMAT(load_date, '%Y-%m-%d') = STR_TO_DATE(:loadDate, '%Y-%m-%d')";
        Map<String, Object> argMap = new HashMap<String, Object>();
        argMap.put("fromCurr", fromCurr);
        argMap.put("toCurr", toCurr);
        argMap.put("loadDate", date);
        return (CurrencyRates) ratesdbJdbcTemplate.queryForObject(sql, argMap, new RatesMapper());
    }

    public CurrencyRates findRatesByFromAndTo(String fromCurr, String toCurr) {

        String sql = "SELECT * FROM CURRENCY_RATES c WHERE c.FROM_CURR = :fromCurr and c.TO_CURR = :toCurr and load_date >= (select DATE_FORMAT(max(load_date), '%Y-%m-%d') FROM CURRENCY_RATES c1 where c1.FROM_CURR = :fromCurr1 and c1.to_curr = :toCurr1)";
        Map<String, Object> argMap = new HashMap<String, Object>();
        argMap.put("fromCurr", fromCurr);
        argMap.put("toCurr", toCurr);
        argMap.put("fromCurr1", fromCurr);
        argMap.put("toCurr1", toCurr);
        return (CurrencyRates) ratesdbJdbcTemplate.queryForObject(sql, argMap, new RatesMapper());


    }


    public void saveRates( CurrencyRates p) {
            String sql = "insert into CURRENCY_RATES(FROM_CURR,TO_CURR,TO_CURR_ALPHA_CODE,TO_CURR_NAME, TO_CURR_NUMERIC_CODE, LOAD_DATE,RATE,INV_RATE) "
            + " VALUES (:fromCurr,:toCurr,:toCurrAlphaCode,:toCurrName, :toCurrNumericCode, :loadDate,:rate,:invRate)";
            SqlParameterSource parameters = new MapSqlParameterSource()
                    .addValue("fromCurr", p.getFromCurrency())
                    .addValue("toCurr", p.getToCurrency())
                    .addValue("toCurrAlphaCode", p.getToCurrAlphaCode())
                    .addValue("toCurrName", p.getToCurrName())
                    .addValue("toCurrNumericCode", p.getToCurrNumbericCode())
                    .addValue("loadDate", p.getDate())
                    .addValue("rate", p.getRate())
                    .addValue("invRate", p.getInvRate());
            int id = ratesdbJdbcTemplate.update(sql,parameters);

    }

    public List<CurrencyRates> listAll() {
        String sql = "SELECT * FROM CURRENCY_RATES";
        return (List<CurrencyRates>) ratesdbJdbcTemplate.query(sql,new RatesMapper());

  
    }

    public void saveExchangeRates( ExchangeRates p) {
        String sql = "insert into EXCHANGE_RATES(CURRENCY_PAIR, CURRENCY_FROM_CD, CURRENCY_TO_CD, PX_MID, PX_ASK, PX_BID, PX_LAST, SOURCE_SYSTEM_CODE, CLOSE_FLAG, TRADE_DATE) "
                + " VALUES (:currencyPair, :fromCurr, :toCurr, :pxMid, :pxAsk, :pxBid, :pxLast, :sourceSystemCode, :closeFlag, :tradeDate)";

        SqlParameterSource parameters = new MapSqlParameterSource()
                .addValue("fromCurr", p.getFromCurrency())
                .addValue("toCurr", p.getToCurrency())
                .addValue("currencyPair", p.getCurrencyPair())
                .addValue("sourceSystemCode", p.getSourceSystemCode())
                .addValue("tradeDate", p.getTradeDate())
                .addValue("closeFlag", p.getCloseFlag())
                .addValue("pxMid", p.getPxMid())
                .addValue("pxBid", p.getPxBid())
                .addValue("pxAsk", p.getPxAsk())
                .addValue("pxLast", p.getPxLast());
        int id = ratesdbJdbcTemplate.update(sql,parameters);

    }

    public List<ExchangeRates> listAllExchRates() {
        String sql = "SELECT * FROM EXCHANGE_RATES";
        return (List<ExchangeRates>) ratesdbJdbcTemplate.query(sql,new ExchangeRatesMapper());
    }

    public ExchangeRates findExchangeRatesFromTo(String fromCurr, String toCurr, String sourceSystem) {
        String sql = "SELECT * FROM EXCHANGE_RATES c WHERE c.CURRENCY_FROM_CD = :fromCurr and c.CURRENCY_TO_CD = :toCurr and c.SOURCE_SYSTEM_CODE = :sourceSystem and c.TRADE_DATE >= (select DATE_FORMAT(max(TRADE_DATE), '%Y-%m-%d') FROM EXCHANGE_RATES c1 where c1.CURRENCY_FROM_CD = :fromCurr1 and c1.CURRENCY_TO_CD = :toCurr1 and c1.SOURCE_SYSTEM_CODE = :sourceSystem1 )";
        Map<String, Object> argMap = new HashMap<String, Object>();
        argMap.put("fromCurr", fromCurr);
        argMap.put("toCurr", toCurr);
        argMap.put("sourceSystem", sourceSystem);
        argMap.put("fromCurr1", fromCurr);
        argMap.put("toCurr1", toCurr);
        argMap.put("sourceSystem1", sourceSystem);
        System.out.println("SQL is : " + sql);
        return (ExchangeRates) ratesdbJdbcTemplate.queryForObject(sql, argMap, new ExchangeRatesMapper());
    }

    public ExchangeRates findExchangeRatesByFromToDate(String fromCurr, String toCurr, String date, String sourceSystem) {
        String sql = "SELECT * FROM EXCHANGE_RATES c WHERE c.CURRENCY_FROM_CD = :fromCurr and c.CURRENCY_TO_CD = :toCurr and c.SOURCE_SYSTEM_CODE = :sourceSystem and DATE_FORMAT(c.TRADE_DATE, '%Y-%m-%d') = STR_TO_DATE(:tradeDate, '%Y-%m-%d')";
        Map<String, Object> argMap = new HashMap<String, Object>();
        argMap.put("fromCurr", fromCurr);
        argMap.put("toCurr", toCurr);
        argMap.put("tradeDate", date);
        argMap.put("sourceSystem", sourceSystem);
        System.out.println("SQL is : " + sql);
        return (ExchangeRates) ratesdbJdbcTemplate.queryForObject(sql, argMap, new ExchangeRatesMapper());
    }


}

class RatesMapper implements RowMapper {

    @Override
    public CurrencyRates mapRow(ResultSet rs, int rowNum) throws SQLException {
        CurrencyRates cr = new CurrencyRates();
        try {
            cr.setFromCurrency(rs.getString("FROM_CURR"));
            cr.setToCurrency(rs.getString("TO_CURR"));
            cr.setToCurrAlphaCode(rs.getString("TO_CURR_ALPHA_CODE"));
            cr.setDate(rs.getDate("LOAD_DATE"));
            cr.setRate(rs.getFloat("RATE"));
            cr.setInvRate(rs.getFloat("INV_RATE"));
            cr.setToCurrName(rs.getString("TO_CURR_NAME"));
            cr.setToCurrNumbericCode(rs.getString("TO_CURR_NUMERIC_CODE"));
            cr.setId(rs.getLong("ID"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cr;
    }

}

class ExchangeRatesMapper implements RowMapper{

    @Override
    public ExchangeRates mapRow(ResultSet rs, int rowNum) throws SQLException {
        ExchangeRates er = new ExchangeRates();

        er.setCurrencyPair(rs.getString("CURRENCY_PAIR"));
        er.setCloseFlag(rs.getString("CLOSE_FLAG"));
        er.setFromCurrency(rs.getString("CURRENCY_FROM_CD"));
        er.setToCurrency(rs.getString("CURRENCY_TO_CD"));
        er.setSourceSystemCode(rs.getString("SOURCE_SYSTEM_CODE"));
        er.setTradeDate(rs.getDate("TRADE_DATE"));
        er.setPxAsk(rs.getDouble("PX_ASK"));
        er.setPxBid(rs.getDouble("PX_BID"));
        er.setPxMid(rs.getDouble("PX_MID"));
        er.setPxLast(rs.getDouble("PX_LAST"));
        System.out.println("result set received: " + er.toString());
        return null;
    }
}