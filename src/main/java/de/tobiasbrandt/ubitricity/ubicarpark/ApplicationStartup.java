package de.tobiasbrandt.ubitricity.ubicarpark;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import de.tobiasbrandt.ubitricity.ubicarpark.service.chargemanagement.ChargeManagementService;
import de.tobiasbrandt.ubitricity.ubicarpark.service.chargepoint.ChargePoint;
import de.tobiasbrandt.ubitricity.ubicarpark.service.chargepoint.ChargePointService;
import de.tobiasbrandt.ubitricity.ubicarpark.service.chargepoint.ChargePointStatus;

@Component
public class ApplicationStartup implements ApplicationListener<ContextRefreshedEvent> {

	private static final Logger logger = LoggerFactory.getLogger(ApplicationStartup.class);

	@Autowired
	private ChargePointService chargePointService;

	@Autowired
	private ChargeManagementService chargeManagementService;

	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		logger.info("Ubi Carpark app started. Initialize ampere management");

		List<ChargePoint> chargePoints = chargePointService.findAll().stream()
				.filter(cp -> cp.getStatus() == ChargePointStatus.OCCUPIED)
				.collect(Collectors.toList());

		chargePoints.stream()
				.map(cp -> cp.getChargeSpeed())
				.forEach(chargeManagementService::reserveCapacity);

		logger.info("Reserved ampere capacity for {} charging points. Total amperes used: {}", chargePoints.size(),
				chargeManagementService.getCurrentlyUsedCapacity());

		logger.info("Done initializing");
	}
}
