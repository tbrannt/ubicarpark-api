package de.tobiasbrandt.ubitricity.ubicarpark.service.chargepoint;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import de.tobiasbrandt.ubitricity.ubicarpark.ApplicationStartup;

@Service
public class ChargePointService {

	private static final Logger logger = LoggerFactory.getLogger(ApplicationStartup.class);

	@Autowired
	private ChargePointRepository chargePointRepository;

	public List<ChargePoint> findAll() {
		return chargePointRepository.findAll();
	}

	@Transactional
	public ChargePoint startCharging(String chargePointName, ChargeSpeed chargeSpeed) {
		ChargePoint chargePoint = findByNameOrThrow(chargePointName);

		if (chargePoint.getStatus() == ChargePointStatus.OCCUPIED) {
			throw new ChargePointBadRequestException("charge point already charging");
		}

		chargePoint.setStatus(ChargePointStatus.OCCUPIED);
		chargePoint.setChargeStartDate(LocalDateTime.now());
		chargePoint.setChargeSpeed(chargeSpeed);

		return chargePoint;
	}

	@Transactional
	public ChargePoint stopCharging(String chargePointName) {
		ChargePoint chargePoint = findByNameOrThrow(chargePointName);

		if (chargePoint.getStatus() == ChargePointStatus.AVAILABLE) {
			throw new ChargePointBadRequestException("charge point already stopped charging");
		}

		chargePoint.setStatus(ChargePointStatus.AVAILABLE);
		chargePoint.setChargeStartDate(null);
		chargePoint.setPreviousChargeSpeed(chargePoint.getChargeSpeed());
		chargePoint.setChargeSpeed(null);

		return chargePoint;
	}

	@Transactional
	public boolean slowDownLongestFastChargingChargePoint() {
		Optional<ChargePoint> fastChargingChargePoint = chargePointRepository
				.findFirstByChargeSpeedOrderByChargeStartDateAsc(ChargeSpeed._20_AMPERES);

		if (fastChargingChargePoint.isPresent()) {
			fastChargingChargePoint.get().setChargeSpeed(ChargeSpeed._10_AMPERES);

			logger.info("Slowed down charging for {} to {}", fastChargingChargePoint.get().getName(),
					ChargeSpeed._10_AMPERES);

			return true;
		} else {
			logger.info("No charging point to slow down found");

			return false;
		}
	}

	/**
	 * Will switch a connection back from slow charging to fast charging. Only done
	 * if a slow charging connection exists.
	 */
	@Transactional
	public boolean upgradeNewestSlowChargingChargePoint() {
		Optional<ChargePoint> slowChargingChargePoint = chargePointRepository
				.findFirstByChargeSpeedOrderByChargeStartDateDesc(ChargeSpeed._10_AMPERES);

		if (slowChargingChargePoint.isPresent()) {
			slowChargingChargePoint.get().setChargeSpeed(ChargeSpeed._20_AMPERES);

			logger.info("Upgraded charging for {} to fast charging {}", slowChargingChargePoint.get().getName(),
					ChargeSpeed._20_AMPERES);

			return true;
		} else {
			logger.info("No charging point to upgrade found");

			return false;
		}
	}

	private ChargePoint findByNameOrThrow(String chargePointName) {
		Optional<ChargePoint> chargePointOpt = chargePointRepository.findByName(chargePointName);

		if (chargePointOpt.isPresent()) {
			return chargePointOpt.get();
		} else {
			throw new ChargePointNotFoundException();
		}
	}

}
