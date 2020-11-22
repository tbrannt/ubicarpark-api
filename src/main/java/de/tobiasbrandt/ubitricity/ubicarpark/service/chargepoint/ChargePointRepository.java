package de.tobiasbrandt.ubitricity.ubicarpark.service.chargepoint;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
interface ChargePointRepository extends JpaRepository<ChargePoint, Long> {
}
