package de.tobiasbrandt.ubitricity.ubicarpark.service.chargepoint;

import java.time.LocalDateTime;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;
import javax.persistence.Version;

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
	private LocalDateTime chargeStartDate;

	@Basic
	@Enumerated(EnumType.STRING)
	private ChargeSpeed chargeSpeed;

	@Version
	private Long version;

	@Transient
	private ChargeSpeed previousChargeSpeed;

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

	public LocalDateTime getChargeStartDate() {
		return chargeStartDate;
	}

	public void setChargeStartDate(LocalDateTime chargeStartDate) {
		this.chargeStartDate = chargeStartDate;
	}

	public ChargeSpeed getChargeSpeed() {
		return chargeSpeed;
	}

	public void setChargeSpeed(ChargeSpeed chargeSpeed) {
		this.chargeSpeed = chargeSpeed;
	}

	public Long getVersion() {
		return version;
	}

	public void setVersion(Long version) {
		this.version = version;
	}

	public ChargeSpeed getPreviousChargeSpeed() {
		return previousChargeSpeed;
	}

	public void setPreviousChargeSpeed(ChargeSpeed previousChargeSpeed) {
		this.previousChargeSpeed = previousChargeSpeed;
	}

	@Override
	public String toString() {
		return "ChargePoint [id=" + id + ", name=" + name + ", status=" + status + ", chargeStartDate="
				+ chargeStartDate + ", chargeSpeed=" + chargeSpeed + ", version=" + version + ", previousChargeSpeed="
				+ previousChargeSpeed + "]";
	}

}
