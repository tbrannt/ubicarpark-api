package de.tobiasbrandt.ubitricity.ubicarpark.service.chargepoint;

import org.springframework.http.HttpStatus;

import de.tobiasbrandt.ubitricity.ubicarpark.UbicarparkApiException;

class ChargePointBadRequestException extends UbicarparkApiException {

	private static final long serialVersionUID = 1L;

	public ChargePointBadRequestException(String message) {
		super(message, HttpStatus.BAD_REQUEST);
	}

}
