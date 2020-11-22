package de.tobiasbrandt.ubitricity.ubicarpark.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import de.tobiasbrandt.ubitricity.ubicarpark.service.ChargeConnectionService;

@RestController
public class ChargeConnectionsController {

	private static final String PATH = "/charge-connections";

	@Autowired
	private ChargeConnectionService chargeConnectionService;

//	@ApiOperation("Get the status of all current charge connections")
	@GetMapping(PATH)
	public void getChargeConnections() {
//		chargeConnectionService.createChargeConnection(...);
		System.out.println(chargeConnectionService); // TODO: delete
	}

//	@ApiOperation("Creates a charging connection for a certain charging point. This is done when a car is plugged into a charging point.")
	@PostMapping(PATH)
	public void createChargeConnection() {
//		chargeConnectionService.createChargeConnection(...);
		System.out.println(chargeConnectionService); // TODO: delete
	}

}
