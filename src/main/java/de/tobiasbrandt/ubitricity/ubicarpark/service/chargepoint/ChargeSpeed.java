package de.tobiasbrandt.ubitricity.ubicarpark.service.chargepoint;

public enum ChargeSpeed {

	// slow charging
	_10_AMPERES(10),
	// fast charging
	_20_AMPERES(20);

	private final double amperes;

	private ChargeSpeed(double amperes) {
		this.amperes = amperes;
	}

	public double getAmperes() {
		return amperes;
	}

}
