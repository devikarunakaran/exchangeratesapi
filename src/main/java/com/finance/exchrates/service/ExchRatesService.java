package com.finance.exchrates.service;

import com.finance.exchrates.db.ExchRatesDBHandler;
import com.finance.exchrates.enums.FileProvider;
import com.finance.exchrates.fileLoader.BloombergFileParser;
import com.finance.exchrates.model.ExchangeRateRequest;
import com.finance.exchrates.model.ExchangeRates;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ExchRatesService {

    @Autowired
    private ExchRatesDBHandler db;

    public ExchRatesService() {

        db = new ExchRatesDBHandler();
    }

    public boolean loadRatesFromFile(String filePath) {
        if (filePath == null || filePath.trim().length() <= 0)
            return false;
        BloombergFileParser bloombergFileParser = new BloombergFileParser();
        List<ExchangeRates> currRates = null;
        try {
            currRates = bloombergFileParser.parse(filePath);

            if (currRates != null)
                for (ExchangeRates c : currRates)
                    System.out.println(c.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (currRates != null) {
            saveRatesToDb(currRates);
            return true;
        } else
            return false;
    }


    public void saveRatesToDb(List<ExchangeRates> currRates) {
        if (currRates != null) {
            System.out.println("Saving Rates to DB");
            for (ExchangeRates c : currRates) {
                System.out.println("Saving Rates for : " + c.getFromCurrency() + " and " + c.getToCurrency());
                saveRates(c);
            }
        }
    }

    public ExchangeRates getRatesFromTo(String from, String to) {

        return db.findExchangeRatesFromTo(from, to, FileProvider.BLOOMBERG.toString());
    }

    public ExchangeRates getSpecificRatesFromToDate(ExchangeRateRequest exch) {
        if (exch != null) {
            ExchangeRates cr = db.findExchangeRatesByFromToDate(exch.getFromCurrencyCode(),
                    exch.getToCurrencyCode(), exch.getTradeDate(), FileProvider.BLOOMBERG.toString());
            if (cr != null) {
				/*exch.setRate(cr.getPxMid());
				exch.setInverseRate(cr.getPxLast());*/
                return cr;
            }
        }
        return null;
    }

    public void saveRates(ExchangeRates p) {
        db.saveExchangeRates(p);
    }

    public List<ExchangeRates> listAll() {

        return db.listAllExchRates();
    }
}
