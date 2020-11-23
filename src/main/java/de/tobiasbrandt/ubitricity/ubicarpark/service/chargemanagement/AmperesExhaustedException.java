package de.tobiasbrandt.ubitricity.ubicarpark.service.chargemanagement;

import org.springframework.http.HttpStatus;

import de.tobiasbrandt.ubitricity.ubicarpark.UbicarparkApiException;

class AmperesExhaustedException extends UbicarparkApiException {

	private static final long serialVersionUID = 1L;

	private static final String MESSAGE = "There's currently no capacity available";

	public AmperesExhaustedException() {
		super(MESSAGE, HttpStatus.CONFLICT);
	}

}
