package com.kardex.domain;

import java.util.List;

public class ResponseAllKardexMain
{
	private int httpStatus;
	private String message;
	private List<MKardexMain> MKardexMainList;

	public ResponseAllKardexMain(int httpStatus, String message, List<MKardexMain> mKardexMainList)
	{
		super();
		this.httpStatus = httpStatus;
		this.message = message;
		MKardexMainList = mKardexMainList;
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

	public List<MKardexMain> getMKardexMainList()
	{
		return MKardexMainList;
	}

	public void setMKardexMainList(List<MKardexMain> mKardexMainList)
	{
		MKardexMainList = mKardexMainList;
	}

	@Override
	public String toString()
	{
		return "ResponseAllKardexDetails [httpStatus=" + httpStatus + ", message=" + message + ", MKardexMainList="
				+ MKardexMainList + "]";
	}
}
