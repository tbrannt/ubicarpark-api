package de.tobiasbrandt.ubitricity.ubicarpark.service.chargepoint;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
interface ChargePointRepository extends JpaRepository<ChargePoint, Long> {

	Optional<ChargePoint> findByName(String name);

	Optional<ChargePoint> findFirstByChargeSpeedOrderByChargeStartDateAsc(ChargeSpeed chargeSpeed);

	Optional<ChargePoint> findFirstByChargeSpeedOrderByChargeStartDateDesc(ChargeSpeed chargeSpeed);

}
