package de.tobiasbrandt.ubitricity.ubicarpark.controller.chargeconnections;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import de.tobiasbrandt.ubitricity.ubicarpark.service.chargepoint.ChargePointStatus;
import de.tobiasbrandt.ubitricity.ubicarpark.service.chargepoint.ChargeSpeed;

public class ChargeConnectionStatusDto {

	private String chargePointName;

	private ChargePointStatus status;

	@JsonInclude(Include.NON_NULL)
	private ChargeSpeed chargeSpeed;

	public String getChargePointName() {
		return chargePointName;
	}

	public void setChargePointName(String chargePointName) {
		this.chargePointName = chargePointName;
	}

	public ChargePointStatus getStatus() {
		return status;
	}

	public void setStatus(ChargePointStatus status) {
		this.status = status;
	}

	public ChargeSpeed getChargeSpeed() {
		return chargeSpeed;
	}

	public void setChargeSpeed(ChargeSpeed chargeSpeed) {
		this.chargeSpeed = chargeSpeed;
	}

}
