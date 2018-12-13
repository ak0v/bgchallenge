package com.bg.restchallange.model;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
@JsonIgnoreProperties
public class Price {

	private String value;
	private LocalDateTime timestamp;

	public Price(String value, LocalDateTime timestamp) {
		super();
		this.value = value;
		this.timestamp = timestamp;
	}
	
	public Price() { 
	}
	
	public LocalDateTime getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(LocalDateTime timestamp) {
		this.timestamp = timestamp;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	
	@Override
	public String toString() {
		return "Price [value=" + value + ", timestamp=" + timestamp + "]";
	}

}
