package com.kardex.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "KardexDetails")
public class MKardexDetails {
	@Id
	@GeneratedValue
	private Long id;
	@NotNull
	private int idKardexMain;
	@NotNull
	@JsonFormat(pattern="dd-mm-yyyy")
	private Date date;
	@NotNull
	private String description;
	@NotNull
	private int unitValue;
	@NotNull
	private int inputQuantity;
	@NotNull
	private int inputValue;
	@NotNull
	private int outputQuantity;
	@NotNull
	private int outputValue;
	@NotNull
	private int balanceQuantity;
	@NotNull
	private int balanceValue;

	public MKardexDetails() {
		super();
	}

	public MKardexDetails(Date date, String description, int unitValue, int inputQuantity, int inputValue,
			int outputQuantity, int outputValue, int balanceQuantity, int balanceValue) {
		super();
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

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getIdKardexMain() {
		return idKardexMain;
	}

	public void setIdKardexMain(int idKardexMain) {
		this.idKardexMain = idKardexMain;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getUnitValue() {
		return unitValue;
	}

	public void setUnitValue(int unitValue) {
		this.unitValue = unitValue;
	}

	public int getInputQuantity() {
		return inputQuantity;
	}

	public void setInputQuantity(int inputQuantity) {
		this.inputQuantity = inputQuantity;
	}

	public int getInputValue() {
		return inputValue;
	}

	public void setInputValue(int inputValue) {
		this.inputValue = inputValue;
	}

	public int getOutputQuantity() {
		return outputQuantity;
	}

	public void setOutputQuantity(int outputQuantity) {
		this.outputQuantity = outputQuantity;
	}

	public int getOutputValue() {
		return outputValue;
	}

	public void setOutputValue(int outputValue) {
		this.outputValue = outputValue;
	}

	public int getBalanceQuantity() {
		return balanceQuantity;
	}

	public void setBalanceQuantity(int balanceQuantity) {
		this.balanceQuantity = balanceQuantity;
	}

	public int getBalanceValue() {
		return balanceValue;
	}

	public void setBalanceValue(int balanceValue) {
		this.balanceValue = balanceValue;
	}
}
