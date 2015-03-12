package com.webapp.shared;

import java.io.Serializable;

public class Results implements Serializable {

	private static final long serialVersionUID = 6982032507851576622L;

	private Boolean isSuccessful;
	private String message;
	private Long startTime;
	private Long stopTime;
	private Integer index;
	private String testType;

	public Results() {

	}

	public Results(Integer position, Long startTime, String testType) {
		this.startTime = startTime;
		this.index = position;
		this.testType = testType;
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
		if (startTime == null || stopTime == null) {
			if (startTime == null)
				startTime = System.currentTimeMillis();
			if (stopTime == null)
				stopTime = System.currentTimeMillis();
			setIsSuccessful(false);
			setMessage("getDuration() called too early");
		}

		return stopTime - startTime;
	}

	public String getTestType() {
		return testType;
	}

	public void setTestType(String testType) {
		this.testType = testType;
	}

	public String format() {
		String display;

		if (stopTime != null && startTime != null)
			display = getDuration() + " ms";
		else
			display = "Something is wrong...";

		return display;
	}

	public boolean isComplete() {
		return (stopTime == null) ? false : true;
	}
}