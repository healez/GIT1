package com.myspace.githook_1;

/**
 * This class was automatically generated by the data modeler tool.
 */

public class ShipmentObject implements java.io.Serializable {

	static final long serialVersionUID = 1L;

	private java.lang.String countryCode;
	private java.lang.String countryName;
	private java.lang.String countryColor;
	private java.lang.String countryNumber;
	private java.lang.String countryNotes;

	public ShipmentObject() {
	}

	public java.lang.String getCountryCode() {
		return this.countryCode;
	}

	public void setCountryCode(java.lang.String countryCode) {
		this.countryCode = countryCode;
	}

	public java.lang.String getCountryName() {
		return this.countryName;
	}

	public void setCountryName(java.lang.String countryName) {
		this.countryName = countryName;
	}

	public java.lang.String getCountryColor() {
		return this.countryColor;
	}

	public void setCountryColor(java.lang.String countryColor) {
		this.countryColor = countryColor;
	}

	public java.lang.String getCountryNumber() {
		return this.countryNumber;
	}

	public void setCountryNumber(java.lang.String countryNumber) {
		this.countryNumber = countryNumber;
	}

	public java.lang.String getCountryNotes() {
		return this.countryNotes;
	}

	public void setCountryNotes(java.lang.String countryNotes) {
		this.countryNotes = countryNotes;
	}

	public ShipmentObject(java.lang.String countryCode,
			java.lang.String countryName, java.lang.String countryColor,
			java.lang.String countryNumber, java.lang.String countryNotes) {
		this.countryCode = countryCode;
		this.countryName = countryName;
		this.countryColor = countryColor;
		this.countryNumber = countryNumber;
		this.countryNotes = countryNotes;
	}

}