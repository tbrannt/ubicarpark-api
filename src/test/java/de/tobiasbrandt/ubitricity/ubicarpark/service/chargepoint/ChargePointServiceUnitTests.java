package de.tobiasbrandt.ubitricity.ubicarpark.service.chargepoint;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class ChargePointServiceUnitTests {

	@Mock
	private ChargePointRepository chargePointRepository;

	@InjectMocks
	private ChargePointService chargePointService;

	@Test
	public void shouldSlowDownLongestFastChargingChargePoint() {
		// given
		ChargePoint cpToSlow = new ChargePoint();
		cpToSlow.setChargeSpeed(ChargeSpeed._20_AMPERES);

		when(chargePointRepository.findFirstByChargeSpeedOrderByChargeStartDateAsc(ChargeSpeed._20_AMPERES))
				.thenReturn(Optional.of(cpToSlow));

		// when
		boolean didSlowDown = chargePointService.slowDownLongestFastChargingChargePoint();

		// then
		assertThat(didSlowDown).isTrue();
		assertThat(cpToSlow.getChargeSpeed()).isEqualTo(ChargeSpeed._10_AMPERES);
	}

	@Test
	public void shouldReturnFalseIfLongestFastChargingChargePointNotFound() {
		// given
		when(chargePointRepository.findFirstByChargeSpeedOrderByChargeStartDateAsc(ChargeSpeed._20_AMPERES))
				.thenReturn(Optional.empty());

		// when
		boolean didSlowDown = chargePointService.slowDownLongestFastChargingChargePoint();

		// then
		assertThat(didSlowDown).isFalse();
	}
}
