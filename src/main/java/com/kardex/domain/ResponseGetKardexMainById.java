package com.kardex.domain;

public class ResponseGetKardexMainById
{
	private int httpStatus;
	private String message;
	private MKardexMain mKardexMain;

	public ResponseGetKardexMainById(int httpStatus, String message, MKardexMain mKardexMain)
	{
		super();
		this.httpStatus = httpStatus;
		this.message = message;
		this.mKardexMain = mKardexMain;
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

	public MKardexMain getmKardexMain()
	{
		return mKardexMain;
	}

	public void setmKardexMain(MKardexMain mKardexMain)
	{
		this.mKardexMain = mKardexMain;
	}

	@Override
	public String toString()
	{
		return "ResponseGetKardexMainById [httpStatus=" + httpStatus + ", message=" + message + ", mKardexMain="
				+ mKardexMain + "]";
	}
}
