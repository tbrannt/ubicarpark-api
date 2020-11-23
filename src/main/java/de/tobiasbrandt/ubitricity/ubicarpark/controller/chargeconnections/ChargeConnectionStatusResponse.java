package de.tobiasbrandt.ubitricity.ubicarpark.controller.chargeconnections;

import java.util.List;

public class ChargeConnectionStatusResponse {

	private List<ChargeConnectionStatusDto> connectionStatus;

	public List<ChargeConnectionStatusDto> getConnectionStatus() {
		return connectionStatus;
	}

	public void setConnectionStatus(List<ChargeConnectionStatusDto> connectionStatus) {
		this.connectionStatus = connectionStatus;
	}

}
