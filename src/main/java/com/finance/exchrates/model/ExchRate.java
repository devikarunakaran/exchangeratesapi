package com.finance.exchrates.model;

public class ExchRate {

	private String fromCurrencyCode;
	private String toCurrencyCode;
	private String tradeDate;
	private float rate;
	private float inverseRate;
	public String getFromCurrencyCode() {
		return fromCurrencyCode;
	}
	public void setFromCurrencyCode(String fromCurrencyCode) {
		this.fromCurrencyCode = fromCurrencyCode;
	}
	public String getToCurrencyCode() {
		return toCurrencyCode;
	}
	public void setToCurrencyCode(String toCurrencyCode) {
		this.toCurrencyCode = toCurrencyCode;
	}
	public String getTradeDate() {
		return tradeDate;
	}
	public void setTradeDate(String tradeDate) {
		this.tradeDate = tradeDate;
	}
	public float getRate() {
		return rate;
	}
	public void setRate(float rate) {
		this.rate = rate;
	}
	public float getInverseRate() {
		return inverseRate;
	}
	public void setInverseRate(float inverseRate) {
		this.inverseRate = inverseRate;
	}
	
	
}
