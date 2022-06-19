package com.finance.exchrates.fileLoader;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.finance.exchrates.model.CurrencyCodeRate;
import com.finance.exchrates.model.CurrencyRates;
import com.finance.exchrates.model.ExchangeRates;
import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class JsonFileParser{

	static final Logger LOGGER = LoggerFactory
			.getLogger(JsonFileParser.class);


    @SuppressWarnings("unchecked")
    public List<CurrencyRates> convertJsonToRates(String filePath) {
        try {
            // converting json to Map
            if (filePath == null || filePath.isEmpty()) {
                filePath = "src/main/resources/data/fx-latest.json";
                return null;
            }
            byte[] mapData = Files.readAllBytes(Paths.get(filePath));
            // byte[] mapData = jsonString.getBytes();
            Map<String, Object> myMap = new HashMap<String, Object>();

            ObjectMapper objectMapper = new ObjectMapper();
            myMap = objectMapper.readValue(mapData, HashMap.class);
            System.out.println("Map is: " + myMap);

            List<CurrencyRates> currRates = new ArrayList<CurrencyRates>();

            myMap.forEach((key, value) -> {
                Gson gson = new Gson();
                String json = gson.toJson(value, LinkedHashMap.class);
                try {
                    CurrencyCodeRate cr = objectMapper.readValue(json, CurrencyCodeRate.class);
                    System.out.println("rates for " + key + " : " + cr.toString());
                    CurrencyRates cur = new CurrencyRates();
                    cur = cur.setCurrRatesIndividual(cr);
                    System.out.println("ccrrates for " + key + " : " + cur.toString());
                    if (Objects.nonNull(cur))
                        currRates.add(cur);

                } catch (JsonMappingException e) {
                    e.printStackTrace();
                } catch (JsonProcessingException e) {
                    e.printStackTrace();
                }
            });

            return currRates;

        } catch (JsonParseException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


}

