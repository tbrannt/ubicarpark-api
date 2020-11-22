package de.tobiasbrandt.ubitricity.ubicarpark.controller.chargeconnections;

import de.tobiasbrandt.ubitricity.ubicarpark.service.chargepoint.ChargePointStatus;
import de.tobiasbrandt.ubitricity.ubicarpark.service.chargepoint.ChargeSpeed;

public class ChargeConnectionStatusDto {

	private String chargingPointName;

	private ChargePointStatus status;

	private ChargeSpeed chargeSpeed;

}
