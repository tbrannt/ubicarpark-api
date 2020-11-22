package de.tobiasbrandt.ubitricity.ubicarpark.service.chargepoint;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class ChargePoint {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	protected Long id;

}
