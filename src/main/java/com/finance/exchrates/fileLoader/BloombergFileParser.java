package com.finance.exchrates.fileLoader;

import com.finance.exchrates.enums.FileProvider;
import com.finance.exchrates.model.ExchangeRates;
import com.finance.exchrates.utils.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.RandomAccessFile;
import java.text.SimpleDateFormat;
import java.util.*;


public class BloombergFileParser implements FileParser {

    static final Logger LOGGER = LoggerFactory
            .getLogger(BloombergFileParser.class);

    public List<ExchangeRates> parse(String fileLocation) throws Exception {

        Map<String, ExchangeRates> currencyRates = new HashMap<String, ExchangeRates>();
        List<ExchangeRates> dailyCurrencyConversionRates = new ArrayList<ExchangeRates>(
                0);
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");

        String line;
        RandomAccessFile file = new RandomAccessFile(fileLocation, "r");

        while ((line = file.readLine()) != null) {
            if (line.startsWith("USD")) {

                String[] lineValues = line.split(" ");

                String[] exchangeValues = lineValues[3].split("\\|");
                if (exchangeValues.length > 3
                        && StringUtils.isNumeric(exchangeValues[3])) {

                    String currencyPair = lineValues[0];
                    String rateType = lineValues[1];
                    if (exchangeValues[6] != null && StringUtils.isNumeric(exchangeValues[6]) && !currencyRates.containsKey(currencyPair)) {
                        if (rateType != null && !rateType.equalsIgnoreCase("CMPL") && Double.parseDouble(exchangeValues[6]) > 0.0) {
                            Date date;
                            try {
                                date = sdf.parse(exchangeValues[7]);
                            } catch (Exception exception) {
                                LOGGER.error(
                                        "BloombergFileParser::parse::exception while parsing date::"
                                                + StringUtils.getStackTrace(exception));
                                continue;
                            }

                            ExchangeRates dailyCurrencyConversionRate = new ExchangeRates();
                            dailyCurrencyConversionRate.setCurrencyPair(currencyPair);
                            dailyCurrencyConversionRate
                                    .setPxMid(Double.parseDouble(exchangeValues[3]));
                            dailyCurrencyConversionRate
                                    .setPxAsk(Double.parseDouble(exchangeValues[4]));
                            dailyCurrencyConversionRate
                                    .setPxBid(Double.parseDouble(exchangeValues[5]));
                            dailyCurrencyConversionRate
                                    .setPxLast(Double.parseDouble(exchangeValues[6]));
                            dailyCurrencyConversionRate.setSourceSystemCode(
                                    FileProvider.BLOOMBERG.toString());
                            dailyCurrencyConversionRate.setCloseFlag("N");
                            dailyCurrencyConversionRate.setTradeDate(date);

                            currencyRates.put(currencyPair, dailyCurrencyConversionRate);
                            dailyCurrencyConversionRates
                                    .add(dailyCurrencyConversionRate);
                        }
                    }
                }

            }
        }

        file.close();

        return dailyCurrencyConversionRates;
    }

}
