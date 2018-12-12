package com.bg.restchallange.model;

import java.time.LocalDateTime;

public class Price {

	private Float value;
	private LocalDateTime timestamp;

	public Price(Float value, LocalDateTime timestamp) {
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
	public Float getValue() {
		return value;
	}
	public void setValue(Float value) {
		this.value = value;
	}
}
