package de.tobiasbrandt.ubitricity.ubicarpark.service.chargepoint;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ChargePointService {

	@Autowired
	private ChargePointRepository chargePointRepository;

	public List<ChargePoint> findAll() {
		return chargePointRepository.findAll();

	}

	@Transactional
	public void createChargePoint() {
		ChargePoint cp = new ChargePoint();

		cp.setChargeSpeed(ChargeSpeed._10_AMPERES);
		cp.setName("CP1");
		cp.setStatus(ChargePointStatus.OCCUPIED);

		chargePointRepository.save(cp);
	}

}
