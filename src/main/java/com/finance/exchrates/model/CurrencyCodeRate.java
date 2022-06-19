package com.finance.exchrates.model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CurrencyCodeRate {
	@JsonProperty("code")
	private String code;
	@JsonProperty("alphaCode")
	private String alphaCode;
	@JsonProperty("numericCode")
	private String numericCode;
	@JsonProperty("name")
	private String name;
	@JsonProperty("rate")
	private float rate;
	@JsonProperty("inverseRate")
	private float inverseRate;
	@JsonProperty("date")
	private Date date;
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getAlphaCode() {
		return alphaCode;
	}
	public void setAlphaCode(String alphaCode) {
		this.alphaCode = alphaCode;
	}
	public String getNumericCode() {
		return numericCode;
	}
	public void setNumericCode(String numericCode) {
		this.numericCode = numericCode;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	@Override
	public String toString() {
		return "CurrencyCodeRate [code=" + code + ", alphaCode=" + alphaCode + ", numericCode=" + numericCode
				+ ", name=" + name + ", rate=" + rate + ", inverseRate=" + inverseRate + ", date=" + date + "]";
	}
	
	
	
}
