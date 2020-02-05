package com.kardex.domain;

import java.util.List;

public class ResponseAllKardexDetails
{
	private int httpStatus;
	private String message;
	private List<MKardexDetails> listKardexDetails;

	public ResponseAllKardexDetails(int httpStatus, String message, List<MKardexDetails> listKardexDetails)
	{
		super();
		this.httpStatus = httpStatus;
		this.message = message;
		this.listKardexDetails = listKardexDetails;
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

	public List<MKardexDetails> getlistKardexDetails()
	{
		return listKardexDetails;
	}

	public void setlistKardexDetails(List<MKardexDetails> listKardexDetails)
	{
		this.listKardexDetails = listKardexDetails;
	}

	@Override
	public String toString()
	{
		return "ResponseAllKardexDetails [httpStatus=" + httpStatus + ", message=" + message + ", listKardexDetails="
				+ listKardexDetails + "]";
	}
}
