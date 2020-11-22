package de.tobiasbrandt.ubitricity.ubicarpark.service.chargepoint;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
interface ChargePointRepository extends CrudRepository<ChargePoint, Long> {
}
