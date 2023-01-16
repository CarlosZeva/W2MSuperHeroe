package com.crud.superheroe;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import com.crud.superheroe.model.dto.SuperHeroeDTO;
import com.crud.superheroe.model.entities.SuperHeroe;
import com.crud.superheroe.service.SuperHeroeService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.server.ResponseStatusException;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertThrows;

@AutoConfigureMockMvc
@SpringBootTest
@Sql(scripts = {"classpath:data.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
class SuperHeroeApplicationTest {

	private final static long ID_UNO= 1L;
	private final static long ID_DIEZ= 10L;
	private final static String PARAM_NOMBRE= "man";
	@Autowired
	private MockMvc mvc;

	@Autowired
	private ObjectMapper objectMapper;

	@Autowired
	private SuperHeroeService service;

	@Test
	//@WithMockUser(username = "user", password = "user")
	void getAll_shouldReturnSuperHeroeList() throws Exception {
		mvc.perform(get("/superHeroe"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$").isArray());
	}

	@Test
		//@WithMockUser(username = "user", password = "user")
	void getById_shouldReturnSuperHeroe() throws Exception {
		Long id = ID_UNO;
		mvc.perform(get("/superHeroe/{id}", id))
				.andExpect(status().isOk());
	}

	@Test
		//@WithMockUser(username = "user", password = "user")
	void getById_shouldReturnNotFound() throws Exception {
		Long id = ID_DIEZ;

		mvc.perform(get("/superHeroe/{id}", id))
				.andExpect(status().isNotFound());
	}

	@Test
		//@WithMockUser(username = "user", password = "user")
	void getByNombre_shouldReturnSuperHeroe() throws Exception {

		mvc.perform(get("/superHeroe/ByParams?nombre=", PARAM_NOMBRE))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$").isNotEmpty());
	}

	@Test
		//@WithMockUser(username = "admin", password = "admin", roles = "ADMIN")
	void update_shouldReturnNoContentAndUpdateOk() throws Exception {
		SuperHeroeDTO superHeroe = new SuperHeroeDTO(ID_UNO, "Zetaman", "Marvel");

		mvc.perform(put("/superHeroe/{id}", ID_UNO)
						.contentType(MediaType.APPLICATION_JSON_VALUE)
						.content(objectMapper.writeValueAsString(superHeroe)))
				.andExpect(status().isNoContent());

		SuperHeroeDTO modificado = service.getById(String.valueOf(ID_UNO));
		assertThat(modificado.getNombre()).isEqualTo("Zetaman");
	}

	@Test
	//@WithMockUser(username = "admin", password = "admin", roles = "ADMIN")
	void update_shouldReturnNotFound() throws Exception {
		SuperHeroe superHeroe = new SuperHeroe(10L, "SuperHeroeNoExiste", "Marvel");

		mvc.perform(put("/superHeroe/{id}", ID_DIEZ)
						.contentType(MediaType.APPLICATION_JSON_VALUE)
						.content(objectMapper.writeValueAsString(superHeroe)))
				.andExpect(status().isNotFound())
				.andExpect(jsonPath("$.status", is(404)));
	}

	@Test
	//@WithMockUser(username = "user", password = "user")
	void delete_shouldNoContentAndDeletionOk() throws Exception {
		Long id = ID_UNO;
		mvc.perform(delete("/superHeroe/{id}", id))
				.andExpect(status().isNoContent());
		assertThrows(ResponseStatusException.class, () -> service.getById(String.valueOf(ID_UNO)));
	}

	@Test
	//@WithMockUser(username = "user", password = "user")
	void delete_shouldReturnNotFound() throws Exception {

		mvc.perform(delete("/superHeroe/{id}", ID_DIEZ))
				.andExpect(status().isNotFound())
				.andExpect(jsonPath("$.status", is(404)));
	}

}
