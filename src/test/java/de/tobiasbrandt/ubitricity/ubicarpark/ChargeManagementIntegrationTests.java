package de.tobiasbrandt.ubitricity.ubicarpark;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
class ChargeManagementIntegrationTests {

	@Autowired
	private MockMvc mockMvc;

	@Test
	void testGetChargeConnections() throws Exception {
		mockMvc.perform(get("/charge-connections")
				.accept("application/json")).andDo(print())
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.connectionStatus", hasSize(10)))
				.andExpect(jsonPath("$.connectionStatus[0].*", hasSize(2)))
				.andExpect(jsonPath("$.connectionStatus[0].status", is("AVAILABLE")));
	}

	@Test
	void testCreateChargeConnection() throws Exception {
		mockMvc.perform(post("/charge-connections")
				.contentType("application/json")
				.param("chargePointName", "CP_01")).andDo(print())
				.andExpect(status().isCreated())
				.andExpect(jsonPath("$.status", is("OCCUPIED")));

		mockMvc.perform(get("/charge-connections")
				.accept("application/json")).andDo(print())
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.connectionStatus", hasSize(10)))
				.andExpect(jsonPath("$.connectionStatus[0].*", hasSize(3)))
				.andExpect(jsonPath("$.connectionStatus[0].status", is("OCCUPIED")));
	}

}
