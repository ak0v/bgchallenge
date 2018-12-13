package com.bg.restchallange.model;

import java.util.List;

public class IEXResponse {

	private String symbol;
	private String companyName;
	private String website;
	private List<Price> prices;
	
	public IEXResponse() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public IEXResponse(Company cmp, List<Price> prices) {
		super();
		this.symbol = cmp.getSymbol();
		this.companyName = cmp.getCompanyName();
		this.website = cmp.getWebsite();
		this.prices = prices;
	}
	
	public String getSymbol() {
		return symbol;
	}
	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public String getWebsite() {
		return website;
	}
	public void setWebsite(String website) {
		this.website = website;
	}
	public List<Price> getPrices() {
		return prices;
	}
	public void setPrices(List<Price> prices) {
		this.prices = prices;
	}
	
}
