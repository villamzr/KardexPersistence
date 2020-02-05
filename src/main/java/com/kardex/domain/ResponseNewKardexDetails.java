package com.kardex.domain;

public class ResponseNewKardexDetails
{
	private int httpStatus;
	private String message;

	public ResponseNewKardexDetails(int httpStatus, String message)
	{
		super();
		this.httpStatus = httpStatus;
		this.message = message;
	}

	public int getHttpStatus()
	{
		return httpStatus;
	}

	public void setHttpStatus(int httpStatus)
	{
		this.httpStatus = httpStatus;
	}

	public String getMessage()
	{
		return message;
	}

	public void setMessage(String message)
	{
		this.message = message;
	}
}
