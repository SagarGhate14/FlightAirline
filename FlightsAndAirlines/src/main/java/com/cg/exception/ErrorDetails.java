package com.cg.exception;

import java.util.Date;

//Model class used to send structured error information back to the client/UI.

public class ErrorDetails {
	private String message;
	private Date timeStamp;
	private String detail;

	public ErrorDetails() {

	}

	public ErrorDetails(String message, Date date, String detail) {
		super();
		this.message = message;
		this.timeStamp = date;
		this.detail = detail;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Date getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(Date timeStamp) {
		this.timeStamp = timeStamp;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}
}
