package com.kardex.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;


@Entity
@Table(name = "KardexMain")
public class MKardexMain {
	@Id
	@GeneratedValue
	private Long id;
	@NotNull
	private String object;
	@NotNull
	private String supplier;
	@NotNull
	private String reference;
	@NotNull
	private String unit;
	@NotNull
	private String location;
	@NotNull
	private int min;
	@NotNull
	private int max;

	public MKardexMain() {
		super();
	}

	public MKardexMain(Long id, String object, String supplier, String reference, String unit, String location,
			Integer min, Integer max) {
		super();
		this.id = id;
		this.object = object;
		this.supplier = supplier;
		this.reference = reference;
		this.unit = unit;
		this.location = location;
		this.min = min;
		this.max = max;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getObject() {
		return object;
	}

	public void setObject(String object) {
		this.object = object;
	}

	public String getSupplier() {
		return supplier;
	}

	public void setSupplier(String supplier) {
		this.supplier = supplier;
	}

	public String getReference() {
		return reference;
	}

	public void setReference(String reference) {
		this.reference = reference;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public int getMin() {
		return min;
	}

	public void setMin(int min) {
		this.min = min;
	}

	public int getMax() {
		return max;
	}

	public void setMax(int max) {
		this.max = max;
	}
}
