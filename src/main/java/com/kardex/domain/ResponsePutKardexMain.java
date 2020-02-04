package com.kardex.domain;

public class ResponsePutKardexMain
{
	private int httpStatus;
	private String message;
	private Long id;

	public ResponsePutKardexMain(int httpStatus, String message, Long id)
	{
		super();
		this.httpStatus = httpStatus;
		this.message = message;
		this.id = id;
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

	public Long getId()
	{
		return id;
	}

	public void setId(Long id)
	{
		this.id = id;
	}

	@Override
	public String toString()
	{
		return "ResponsePutKardexMain [httpStatus=" + httpStatus + ", message=" + message + ", id=" + id + "]";
	}
}
