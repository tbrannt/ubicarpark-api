package de.tobiasbrandt.ubitricity.ubicarpark.service.chargepoint;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class ChargePoint {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Basic(optional = false)
	private String name;

	@Basic(optional = false)
	@Enumerated(EnumType.STRING)
	private ChargePointStatus status;

	@Basic
	@Enumerated(EnumType.STRING)
	private ChargeSpeed chargeSpeed;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ChargePointStatus getStatus() {
		return status;
	}

	public void setStatus(ChargePointStatus status) {
		this.status = status;
	}

	public ChargeSpeed getChargeSpeed() {
		return chargeSpeed;
	}

	public void setChargeSpeed(ChargeSpeed chargeSpeed) {
		this.chargeSpeed = chargeSpeed;
	}

	@Override
	public String toString() {
		return "ChargePoint [id=" + id + ", name=" + name + ", status=" + status + ", chargeSpeed=" + chargeSpeed + "]";
	}

}
