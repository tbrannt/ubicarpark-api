package de.tobiasbrandt.ubitricity.ubicarpark.controller.chargeconnections;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import de.tobiasbrandt.ubitricity.ubicarpark.service.chargepoint.ChargePoint;
import de.tobiasbrandt.ubitricity.ubicarpark.service.chargepoint.ChargePointService;
import io.swagger.annotations.ApiOperation;

@RestController
public class ChargeConnectionsController {

	private static final String PATH = "/charge-connections";

	@Autowired
	private ChargePointService chargePointService;

	@ApiOperation("Get the status of all current charge connections")
	@GetMapping(PATH)
	public ChargeConnectionStatusResponse getChargeConnections() {
		List<ChargePoint> chargePoints = chargePointService.findAll();
		System.out.println(chargePoints); // TODO: delete
		return null;
	}

	@ApiOperation("Creates a charging connection for a certain charging point. This is done when a car is plugged into a charging point.")
	@PostMapping(PATH)
	public void createChargeConnection() {
//		chargeConnectionService.createChargeConnection(...);
		System.out.println(chargePointService); // TODO: delete
	}

}
