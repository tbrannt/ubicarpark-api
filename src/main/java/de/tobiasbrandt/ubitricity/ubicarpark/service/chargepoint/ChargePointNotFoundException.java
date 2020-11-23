package de.tobiasbrandt.ubitricity.ubicarpark.service.chargepoint;

import org.springframework.http.HttpStatus;

import de.tobiasbrandt.ubitricity.ubicarpark.UbicarparkApiException;

class ChargePointNotFoundException extends UbicarparkApiException {

	private static final long serialVersionUID = 1L;

	private static final String MESSAGE = "Charge point could not be found";

	public ChargePointNotFoundException() {
		super(MESSAGE, HttpStatus.NOT_FOUND);
	}

}
