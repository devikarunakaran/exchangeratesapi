package com.finance.exchrates.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class ExchangeRates {

	private String fromCurrency;
	private String toCurrency;
	private String currencyPair;
	private double pxMid;
	private double pxAsk;
	private double pxBid;
	private double pxLast;
	private String sourceSystemCode;
	private String closeFlag;
	@JsonIgnore
	private String tradeDate;

	public ExchangeRates() {
		super();
	}

	public void setFromCurrency(String fromCurrency) {
		this.fromCurrency = fromCurrency;
	}

	public String getFromCurrency() {
		return fromCurrency;
	}

	public String getToCurrency() {
		return toCurrency;
	}

	public String getCurrencyPair() {
		return currencyPair;
	}

	public void setToCurrency(String toCurrency) {
		this.toCurrency = toCurrency;
	}

	public void setCurrencyPair(String currencyPair) {
		this.currencyPair = currencyPair;
		this.fromCurrency = currencyPair.substring(0,
				Math.min(currencyPair.length(), 3));
		this.toCurrency = currencyPair.substring(currencyPair.length() - 3);
	}

	public double getPxMid() {
		return pxMid;
	}

	public void setPxMid(double pxMid) {
		this.pxMid = pxMid;
	}

	public double getPxAsk() {
		return pxAsk;
	}

	public void setPxAsk(double pxAsk) {
		this.pxAsk = pxAsk;
	}

	public double getPxBid() {
		return pxBid;
	}

	public void setPxBid(double pxBid) {
		this.pxBid = pxBid;
	}

	public double getPxLast() {
		return pxLast;
	}

	public void setPxLast(double pxLast) {
		this.pxLast = pxLast;
	}

	public String getSourceSystemCode() {
		return sourceSystemCode;
	}

	public void setSourceSystemCode(String sourceSystemCode) {
		this.sourceSystemCode = sourceSystemCode;
	}

	public String getCloseFlag() {
		return closeFlag;
	}

	public void setCloseFlag(String closeFlag) {
		this.closeFlag = closeFlag;
	}

	public String getTradeDate() {
		return tradeDate;
	}

	public void setTradeDate(Date tradeDate) {
		SimpleDateFormat dateformatyyyyMMdd = new SimpleDateFormat("yyyyMMdd");
		String date_to_string = dateformatyyyyMMdd.format(tradeDate);
		this.tradeDate = date_to_string;
	}

	public void setTradeDate(String tradeDate) {
		this.tradeDate = tradeDate;
	}

	public static Map<String, String> getBbgDailyCurrencyConversionRateMap()
			throws Throwable {
		Map<String, String> colMap = new HashMap<String, String>();
		colMap.put("fromCurrency", "currency_from_cd");
		colMap.put("toCurrency", "currency_to_cd");
		colMap.put("currencyPair", "currency_pair");
		colMap.put("pxMid", "px_mid");
		colMap.put("pxAsk", "px_ask");
		colMap.put("pxBid", "px_bid");
		colMap.put("pxLast", "px_last");
		colMap.put("sourceSystemCode", "source_system_code");
		colMap.put("closeFlag", "close_flag");
		colMap.put("tradeDate", "trade_date");

		return colMap;
	}
	
	public static Map<String, String> getMcDailyCurrencyConversionRateMap()
			throws Throwable {
		Map<String, String> colMap = new HashMap<String, String>();
		colMap.put("fromCurrency", "currency_from_cd");
		colMap.put("toCurrency", "currency_to_cd");
		colMap.put("currencyPair", "currency_pair");
		colMap.put("pxAsk", "px_ask");
		colMap.put("pxBid", "px_bid");
		colMap.put("pxLast", "px_last");
		colMap.put("sourceSystemCode", "source_system_code");
		colMap.put("tradeDate", "trade_date");

		return colMap;
	}

	@Override
	public String toString() {
		return "ExchangeRates [fromCurrency=" + fromCurrency + ", toCurrency="
				+ toCurrency + ", currencyPair=" + currencyPair + ", pxMid="
				+ pxMid + ", pxAsk=" + pxAsk + ", pxBid=" + pxBid + ", pxLast="
				+ pxLast + ", sourceSystemCode=" + sourceSystemCode
				+ ", closeFlag=" + closeFlag + ", tradeDate=" + tradeDate + "]";
	}

}
