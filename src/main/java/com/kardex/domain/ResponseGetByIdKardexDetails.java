package com.kardex.domain;

public class ResponseGetByIdKardexDetails
{
	private int httpStatus;
	private String message;
	private MKardexDetails mKardexDetails;

	public ResponseGetByIdKardexDetails(int httpStatus, String message, MKardexDetails mKardexDetails)
	{
		super();
		this.httpStatus = httpStatus;
		this.message = message;
		this.mKardexDetails = mKardexDetails;
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

	public MKardexDetails getmKardexDetails()
	{
		return mKardexDetails;
	}

	public void setmKardexDetails(MKardexDetails mKardexDetails)
	{
		this.mKardexDetails = mKardexDetails;
	}

	@Override
	public String toString()
	{
		return "ResponseGetByIdKardexDetails [httpStatus=" + httpStatus + ", message=" + message + ", mKardexDetails="
				+ mKardexDetails + "]";
	}
}
