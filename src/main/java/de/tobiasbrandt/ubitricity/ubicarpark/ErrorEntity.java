package de.tobiasbrandt.ubitricity.ubicarpark;

public class ErrorEntity {

	private String error;

	private String exceptionType;

	public ErrorEntity(UbicarparkApiException e) {
		this.error = e.getMessage();
		this.exceptionType = e.getClass().getSimpleName();
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public String getExceptionType() {
		return exceptionType;
	}

	public void setExceptionType(String exceptionType) {
		this.exceptionType = exceptionType;
	}

}
