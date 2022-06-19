package com.finance.exchrates.fileLoader;

import com.finance.exchrates.model.ExchangeRates;

import java.util.List;


public interface FileParser {

	public List<ExchangeRates> parse(String fileLocation) throws Exception;

}
