package de.tobiasbrandt.ubitricity.ubicarpark.controller.chargeconnections;

import java.util.List;
import java.util.stream.Collectors;

import de.tobiasbrandt.ubitricity.ubicarpark.service.chargepoint.ChargePoint;

public class ChargeConnectionStatusDtoMapper {

	public static ChargeConnectionStatusDto fromChargePoint(ChargePoint chargePoint) {
		ChargeConnectionStatusDto dto = new ChargeConnectionStatusDto();

		dto.setChargeSpeed(chargePoint.getChargeSpeed());
		dto.setChargePointName(chargePoint.getName());
		dto.setStatus(chargePoint.getStatus());

		return dto;
	}

	public static List<ChargeConnectionStatusDto> fromChargePoints(List<ChargePoint> chargePoints) {
		return chargePoints.stream().map(ChargeConnectionStatusDtoMapper::fromChargePoint)
				.collect(Collectors.toList());
	}

}
