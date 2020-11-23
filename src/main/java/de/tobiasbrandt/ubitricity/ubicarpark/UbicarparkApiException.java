package de.tobiasbrandt.ubitricity.ubicarpark;

import org.springframework.http.HttpStatus;

public class UbicarparkApiException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	private final HttpStatus httpStatus;

	public UbicarparkApiException(String msg, HttpStatus httpStatus) {
		super(msg);
		this.httpStatus = httpStatus;
	}

	public HttpStatus getHttpStatus() {
		return httpStatus;
	}

}
