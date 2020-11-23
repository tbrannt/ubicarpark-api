package de.tobiasbrandt.ubitricity.ubicarpark.service.chargemanagement;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import de.tobiasbrandt.ubitricity.ubicarpark.ApplicationStartup;
import de.tobiasbrandt.ubitricity.ubicarpark.service.chargepoint.ChargePoint;
import de.tobiasbrandt.ubitricity.ubicarpark.service.chargepoint.ChargePointService;
import de.tobiasbrandt.ubitricity.ubicarpark.service.chargepoint.ChargeSpeed;

@Service
public class ChargeManagementService {

	private static final Logger logger = LoggerFactory.getLogger(ApplicationStartup.class);

	private static final long TOTAL_AMPERES_CAPACITY = 100L;

	private long currentlyUsedCapacity = 0L;

	@Autowired
	private ChargePointService chargePointService;

	@Transactional
	public ChargePoint startCharging(String chargePointName) {
		if (hasCapacityAvailable(ChargeSpeed._20_AMPERES)) {
			ChargePoint chargePoint = chargePointService.startCharging(chargePointName, ChargeSpeed._20_AMPERES);
			reserveCapacity(ChargeSpeed._20_AMPERES);

			logger.info("Started charging {} with {}", chargePointName, ChargeSpeed._20_AMPERES);

			return chargePoint;
		} else if (hasCapacityAvailable(ChargeSpeed._10_AMPERES)) {

			ChargeSpeed newChargeSpeed;
			if (chargePointService.slowDownLongestFastChargingChargePoint()) {
				newChargeSpeed = ChargeSpeed._20_AMPERES;
			} else {
				newChargeSpeed = ChargeSpeed._10_AMPERES;
			}

			ChargePoint chargePoint = chargePointService.startCharging(chargePointName, newChargeSpeed);
			reserveCapacity(ChargeSpeed._10_AMPERES);

			logger.info("Started charging {} with {}", chargePointName, newChargeSpeed);

			return chargePoint;
		} else {

			ChargeSpeed newChargeSpeed;
			if (chargePointService.slowDownLongestFastChargingChargePoint()) {
				if (chargePointService.slowDownLongestFastChargingChargePoint()) {
					newChargeSpeed = ChargeSpeed._20_AMPERES;
				} else {
					newChargeSpeed = ChargeSpeed._10_AMPERES;
				}
			} else {
				throw new AmperesExhaustedException();
			}

			ChargePoint chargePoint = chargePointService.startCharging(chargePointName, newChargeSpeed);

			logger.info("Started charging {} with {}", chargePointName, newChargeSpeed);

			return chargePoint;
		}
	}

	@Transactional
	public ChargePoint stopCharging(String chargePointName) {
		ChargePoint chargePoint = chargePointService.stopCharging(chargePointName);
		freeCapacity(chargePoint.getPreviousChargeSpeed());

		logger.info("Stopped charging {}", chargePointName);

		return chargePoint;
	}

	public synchronized void reserveCapacity(ChargeSpeed chargeSpeedToReserve) {
		if ((currentlyUsedCapacity + chargeSpeedToReserve.getAmperes()) > TOTAL_AMPERES_CAPACITY) {
			throw new AmperesExhaustedException();
		}

		currentlyUsedCapacity += chargeSpeedToReserve.getAmperes();
	}

	public synchronized void freeCapacity(ChargeSpeed chargeSpeedToFree) {
		if (chargeSpeedToFree.getAmperes() > currentlyUsedCapacity) {
			throw new RuntimeException("Cannot free more capacity than used");
		}

		currentlyUsedCapacity -= chargeSpeedToFree.getAmperes();
	}

	public boolean hasCapacityAvailable(ChargeSpeed chargeSpeed) {
		return currentlyUsedCapacity + chargeSpeed.getAmperes() <= TOTAL_AMPERES_CAPACITY;
	}

	public long getCurrentlyUsedCapacity() {
		return currentlyUsedCapacity;
	}

}
