package de.tobiasbrandt.ubitricity.ubicarpark.service.chargepoint;

public enum ChargeSpeed {

	// slow charging
	_10_AMPERES(10),
	// fast charging
	_20_AMPERES(20);

	private final long amperes;

	private ChargeSpeed(long amperes) {
		this.amperes = amperes;
	}

	public long getAmperes() {
		return amperes;
	}
}
