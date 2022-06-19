package com.finance.exchrates.model;

import java.util.Date;
import java.util.Objects;
/*
@Entity
@Table(name = "\"CURRENCY_RATES\"")*/
public class CurrencyRates {

	/*@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)*/
	private long id;
	//@Column(name = "FROM_CURR", length = 10, nullable = false, unique = false)
	private String fromCurrency = "USD";
	//@Column(name = "TO_CURR", length = 10, nullable = false, unique = false)
	private String toCurrency;
	//@Column(name = "TO_CURR_ALPHA_CODE", length = 10, nullable = false, unique = false)
	private String toCurrAlphaCode;
	//@Column(name = "TO_CURR_NUMERIC_CODE", length = 10, nullable = false, unique = false)
	private String toCurrNumbericCode;
	//@Column(name = "TO_CURR_NAME", length = 50, nullable = false, unique = false)
	private String toCurrName;
	//@Column(name = "RATE")
	private float rate;
	//@Column(name = "INV_RATE")
	private float invRate;
	//@Column(name = "LOAD_DATE")
	private Date date;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getFromCurrency() {
		return fromCurrency;
	}

	public void setFromCurrency(String fromCurrency) {
		this.fromCurrency = fromCurrency;
	}

	public String getToCurrency() {
		return toCurrency;
	}

	public void setToCurrency(String toCurrency) {
		this.toCurrency = toCurrency;
	}

	public String getToCurrAlphaCode() {
		return toCurrAlphaCode;
	}

	public void setToCurrAlphaCode(String toCurrAlphaCode) {
		this.toCurrAlphaCode = toCurrAlphaCode;
	}

	public String getToCurrNumbericCode() {
		return toCurrNumbericCode;
	}

	public void setToCurrNumbericCode(String toCurrNumbericCode) {
		this.toCurrNumbericCode = toCurrNumbericCode;
	}

	public String getToCurrName() {
		return toCurrName;
	}

	public void setToCurrName(String toCurrName) {
		this.toCurrName = toCurrName;
	}

	public float getRate() {
		return rate;
	}

	public void setRate(float rate) {
		this.rate = rate;
	}

	public float getInvRate() {
		return invRate;
	}

	public void setInvRate(float invRate) {
		this.invRate = invRate;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public CurrencyRates setCurrRatesIndividual(CurrencyCodeRate ccr) {
		if (Objects.nonNull(ccr)) {
			CurrencyRates cr = new CurrencyRates();
			System.out.println(ccr.toString());
			cr.setDate(ccr.getDate());
			cr.setInvRate(ccr.getInverseRate());
			cr.setRate(ccr.getRate());
			cr.setToCurrency(ccr.getCode());
			cr.setToCurrAlphaCode(ccr.getAlphaCode());
			cr.setToCurrName(ccr.getName());
			cr.setToCurrNumbericCode(ccr.getNumericCode());
			System.out.println(cr.toString());
			return cr;
		} else
			return null;
	}

	@Override
	public String toString() {
		return "CurrencyRates [id=" + id + ", fromCurrency=" + fromCurrency + ", toCurrency=" + toCurrency
				+ ", toCurrAlphaCode=" + toCurrAlphaCode + ", toCurrNumbericCode=" + toCurrNumbericCode
				+ ", toCurrName=" + toCurrName + ", rate=" + rate + ", invRate=" + invRate + ", date=" + date + "]";
	}
	
	

}
