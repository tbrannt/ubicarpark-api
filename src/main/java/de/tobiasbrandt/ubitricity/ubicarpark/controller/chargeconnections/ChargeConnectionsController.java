package de.tobiasbrandt.ubitricity.ubicarpark.controller.chargeconnections;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import de.tobiasbrandt.ubitricity.ubicarpark.service.chargemanagement.ChargeManagementService;
import de.tobiasbrandt.ubitricity.ubicarpark.service.chargepoint.ChargePoint;
import de.tobiasbrandt.ubitricity.ubicarpark.service.chargepoint.ChargePointService;
import io.swagger.annotations.ApiOperation;

@RestController
public class ChargeConnectionsController {

	private static final String PATH = "/charge-connections";

	@Autowired
	private ChargeManagementService chargeManagementService;

	@Autowired
	private ChargePointService chargePointService;

	@ApiOperation("Get the status of all current charge connections")
	@GetMapping(PATH)
	public ChargeConnectionStatusResponse getChargeConnections() {
		List<ChargePoint> chargePoints = chargePointService.findAll();

		ChargeConnectionStatusResponse rs = new ChargeConnectionStatusResponse();
		rs.setConnectionStatus(ChargeConnectionStatusDtoMapper.fromChargePoints(chargePoints));

		return rs;
	}

	@ApiOperation("Creates a charging connection for a certain charging point. This is done when a car is plugged into a charging point.")
	@PostMapping(PATH)
	public ChargeConnectionStatusDto createChargeConnection(
			@RequestParam(value = "chargePointName", required = true) String chargePointName) {
		ChargePoint chargePoint = chargeManagementService.startCharging(chargePointName);
		return ChargeConnectionStatusDtoMapper.fromChargePoint(chargePoint);
	}

	@ApiOperation("Removes a charging connection of a certain charging point. This is done when a car is unplugged from a charging point.")
	@DeleteMapping(PATH)
	public ChargeConnectionStatusDto removeChargeConnection(
			@RequestParam(value = "chargePointName", required = true) String chargePointName) {
		ChargePoint chargePoint = chargeManagementService.stopCharging(chargePointName);
		return ChargeConnectionStatusDtoMapper.fromChargePoint(chargePoint);
	}

}
