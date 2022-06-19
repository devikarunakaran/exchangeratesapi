package com.finance.exchrates.service;

import com.finance.exchrates.db.ExchRatesDBHandler;
import com.finance.exchrates.fileLoader.JsonFileParser;
import com.finance.exchrates.model.CurrencyRates;
import com.finance.exchrates.model.ExchRate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class CurrRatesService {

	@Autowired
	private ExchRatesDBHandler db;

	public CurrRatesService(){

		db = new ExchRatesDBHandler();
	}

	public boolean loadRatesFromFile(String filePath) {
		if (filePath == null || filePath.trim().length() <= 0)
			return false;
		JsonFileParser rl = new JsonFileParser();
		List<CurrencyRates> currRates = rl.convertJsonToRates(filePath);
		if (currRates != null)
			for (CurrencyRates c : currRates)
				System.out.println(c.toString());

		if (currRates != null) {
			saveRatesToDb(currRates);
			return true;
		} else
			return false;
	}
	
	

	public void saveRatesToDb(List<CurrencyRates> currRates) {
		if (currRates != null) {
			System.out.println("Saving Rates to DB");
			for (CurrencyRates c : currRates) {
				System.out.println("Saving Rates for : " + c.getFromCurrency() + " and " + c.getToCurrency());
				saveRates(c);
			}
		}
	}

	public CurrencyRates getRatesFromTo(String from, String to) {

		return db.findRatesByFromAndTo(from, to);
	}

	public ExchRate getSpecificRatesFromToDate(ExchRate exch) {
		if (exch != null) {
			CurrencyRates cr = db.findSpecificRatesByFromAndToAndDate(exch.getFromCurrencyCode(),
					exch.getToCurrencyCode(), exch.getTradeDate());
			if (cr != null) {
				exch.setRate(cr.getRate());
				exch.setInverseRate(cr.getInvRate());
				return exch;
			}
		}
		return null;
	}

	public void saveRates(CurrencyRates p) {
		db.saveRates(p);
	}

	public List<CurrencyRates> listAll() {

		return db.listAll();
	}
}
