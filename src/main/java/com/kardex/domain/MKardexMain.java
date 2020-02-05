package com.kardex.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;


@Entity(name = "KardexMain")
@Table(name = "KardexMain")
public class MKardexMain {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="IdKardexMain")
	private Long id;
	@OneToMany(targetEntity = MKardexDetails.class)
	List<MKardexDetails> mKardexDetails = new ArrayList<MKardexDetails>();
	@NotNull
	@Column(name="Object")
	private String object;
	@NotNull
	@Column(name="Supplier")
	private String supplier;
	@NotNull
	@Column(name="Reference")
	private String reference;
	@NotNull
	@Column(name="Unit")
	private String unit;
	@NotNull
	@Column(name="Location")
	private String location;
	@NotNull
	@Column(name="Minimum")
	private int min;
	@NotNull
	@Column(name="Maximum")
	private int max;

	public MKardexMain() {
	}

	public MKardexMain(Long id, List<MKardexDetails> mKardexDetails, @NotNull String object, @NotNull String supplier,
			@NotNull String reference, @NotNull String unit, @NotNull String location, @NotNull int min,
			@NotNull int max)
	{
		super();
		this.id = id;
		this.mKardexDetails = mKardexDetails;
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

	public List<MKardexDetails> getmKardexDetails()
	{
		return mKardexDetails;
	}

	public void setmKardexDetails(List<MKardexDetails> mKardexDetails)
	{
		this.mKardexDetails = mKardexDetails;
	}
}
