package com.cg.entity;

public enum AirlineClass {
	ECONOMY("Economy", 1.0), PREMIUM_ECONOMY("Premium Economy", 1.5), BUSINESS("Business", 2.0),
	FIRST_CLASS("First Class", 4.0);

	private final String className;
	private final double priceFactor;

	private AirlineClass(String className, double priceFactor) {
		this.className = className;
		this.priceFactor = priceFactor;
	}

	public String getClassName() {
		return className;
	}

	public double getPriceFactor() {
		return priceFactor;
	}

}
