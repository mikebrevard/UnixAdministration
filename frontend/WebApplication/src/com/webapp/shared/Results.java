package com.webapp.shared;

import java.io.Serializable;

public class Results implements Serializable {

	private static final long serialVersionUID = 6982032507851576622L;

	private Boolean isSuccessful;
	private String message;
	private Long timestamp;
	private Long startTime;
	private Long stopTime;
	private Integer index;

	Results() {

	}

	Results(Boolean isSuccessful, String message, Long timestamp) {
		this.isSuccessful = isSuccessful;
		this.message = message;
		this.timestamp = timestamp;
	}

	public Results(Integer position, Long startTime) {
		this.startTime = startTime;
		this.index = position;
	}

	public Boolean getIsSuccessful() {
		return isSuccessful;
	}

	public void setIsSuccessful(Boolean isSuccessful) {
		this.isSuccessful = isSuccessful;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Long timestamp) {
		this.timestamp = timestamp;
	}

	public Long getStartTime() {
		return startTime;
	}

	public void setStartTime(Long startTime) {
		this.startTime = startTime;
	}

	public Long getStopTime() {
		return stopTime;
	}

	public void setStopTime(Long stopTime) {
		this.stopTime = stopTime;
	}

	public Integer getIndex() {
		return index;
	}

	public void setIndex(Integer index) {
		this.index = index;
	}

	public Long getDuration() {
		return stopTime - startTime;
	}
}