package com.kardex.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity(name = "KardexDetails")
@Table(name = "KardexDetails")
public class MKardexDetails
{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "IdKardexDetails")
	private Long id;
	@NotNull
	@ManyToOne
	@JoinColumn(name = "IdKardexMain", referencedColumnName = "IdKardexMain")
	private MKardexMain idKardexMain;
	@NotNull
	@JsonFormat(pattern = "dd-mm-yyyy")
	@Column(name = "Date")
	private Date date;
	@NotNull
	@Column(name = "Description")
	private String description;
	@NotNull
	@Column(name = "UnitValue")
	private int unitValue;
	@NotNull
	@Column(name = "InputQuantity")
	private int inputQuantity;
	@NotNull
	@Column(name = "InputValue")
	private int inputValue;
	@NotNull
	@Column(name = "OutputQuantity")
	private int outputQuantity;
	@NotNull
	@Column(name = "OutputValue")
	private int outputValue;
	@NotNull
	@Column(name = "BalanceQuantity")
	private int balanceQuantity;
	@NotNull
	@Column(name = "BalanceValue")
	private int balanceValue;

	public MKardexDetails()
	{
	}

	
	public MKardexDetails(Long id, @NotNull MKardexMain idKardexMain, @NotNull Date date, @NotNull String description,
			@NotNull int unitValue, @NotNull int inputQuantity, @NotNull int inputValue, @NotNull int outputQuantity,
			@NotNull int outputValue, @NotNull int balanceQuantity, @NotNull int balanceValue)
	{
		super();
		this.id = id;
		this.idKardexMain = idKardexMain;
		this.date = date;
		this.description = description;
		this.unitValue = unitValue;
		this.inputQuantity = inputQuantity;
		this.inputValue = inputValue;
		this.outputQuantity = outputQuantity;
		this.outputValue = outputValue;
		this.balanceQuantity = balanceQuantity;
		this.balanceValue = balanceValue;
	}


	public Long getId()
	{
		return id;
	}

	public void setId(Long id)
	{
		this.id = id;
	}

	public MKardexMain getIdKardexMain()
	{
		return idKardexMain;
	}

	public void setIdKardexMain(MKardexMain idKardexMain)
	{
		this.idKardexMain = idKardexMain;
	}

	public Date getDate()
	{
		return date;
	}

	public void setDate(Date date)
	{
		this.date = date;
	}

	public String getDescription()
	{
		return description;
	}

	public void setDescription(String description)
	{
		this.description = description;
	}

	public int getUnitValue()
	{
		return unitValue;
	}

	public void setUnitValue(int unitValue)
	{
		this.unitValue = unitValue;
	}

	public int getInputQuantity()
	{
		return inputQuantity;
	}

	public void setInputQuantity(int inputQuantity)
	{
		this.inputQuantity = inputQuantity;
	}

	public int getInputValue()
	{
		return inputValue;
	}

	public void setInputValue(int inputValue)
	{
		this.inputValue = inputValue;
	}

	public int getOutputQuantity()
	{
		return outputQuantity;
	}

	public void setOutputQuantity(int outputQuantity)
	{
		this.outputQuantity = outputQuantity;
	}

	public int getOutputValue()
	{
		return outputValue;
	}

	public void setOutputValue(int outputValue)
	{
		this.outputValue = outputValue;
	}

	public int getBalanceQuantity()
	{
		return balanceQuantity;
	}

	public void setBalanceQuantity(int balanceQuantity)
	{
		this.balanceQuantity = balanceQuantity;
	}

	public int getBalanceValue()
	{
		return balanceValue;
	}

	public void setBalanceValue(int balanceValue)
	{
		this.balanceValue = balanceValue;
	}
}
