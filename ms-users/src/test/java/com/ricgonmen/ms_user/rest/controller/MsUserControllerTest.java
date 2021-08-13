package com.ricgonmen.ms_user.rest.controller;

import static org.hamcrest.CoreMatchers.any;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ricgonmen.ms_user.rest.dto.CreateUserDTO;
import com.ricgonmen.ms_user.rest.dto.UserDTO;
import com.ricgonmen.ms_user.rest.excepcion.UserNotFoundException;
import com.ricgonmen.ms_user.rest.model.User.Gender;
import com.ricgonmen.ms_user.service.impl.MsUserServiceImpl;

@ExtendWith(SpringExtension.class)
@WebMvcTest(MsUserController.class)
class MsUserControllerTest {

	@Autowired
	WebApplicationContext webApplicationContext;

	@MockBean
	MsUserServiceImpl msUserService;

	@Autowired
	MockMvc mockMvc;

	protected String mapToJson(Object obj) throws JsonProcessingException {
		ObjectMapper objectMapper = new ObjectMapper();
		return objectMapper.writeValueAsString(obj);
	}

	protected <T> T mapFromJson(String json, Class<T> clazz)
			throws JsonParseException, JsonMappingException, IOException {

		ObjectMapper objectMapper = new ObjectMapper();
		return objectMapper.readValue(json, clazz);
	}

	/**
	 * Test method for
	 * {@link com.ricgonmen.ms_user.rest.controller.MsUserController#obtenerTodos()}.
	 */
	@Test
	final void testObtenerTodos() throws Exception {
		UserDTO user = new UserDTO(Long.valueOf(1), "username_", "name_", "email_", Gender.MALE, "picture_");

		List<UserDTO> list = Arrays.asList(user);

		Mockito.when(msUserService.getUsers()).thenReturn(list);

		mockMvc.perform(get("/api/user")).andExpect(status().isOk()).andExpect(jsonPath("$", Matchers.hasSize(1)))
				.andExpect(jsonPath("$[0].username", Matchers.is("username_")));
	}

	/**
	 * Test method for
	 * {@link com.ricgonmen.ms_user.rest.controller.MsUserController#obtenerUnoPorUsername(java.lang.String)}.
	 */
	@Test
	final void testObtenerUnoPorUsernameExistente() throws Exception {
		UserDTO user = new UserDTO(Long.valueOf(1), "username_", "name_", "email_", Gender.MALE, "picture_");

		Mockito.when(msUserService.getUser("username_")).thenReturn(user);

		mockMvc.perform(get("/api/user/username_")).andExpect(status().isOk())
				.andExpect(jsonPath("$.username", Matchers.is("username_")));
	}

	/**
	 * Test method for
	 * {@link com.ricgonmen.ms_user.rest.controller.MsUserController#obtenerUnoPorUsername(java.lang.String)}.
	 */
	@Test
	final void testObtenerUnoPorUsernameNoExistente() throws Exception {
		Mockito.when(msUserService.getUser("username_")).thenThrow(new UserNotFoundException("username_"));

		mockMvc.perform(get("/api/user/username_")).andExpect(status().isNotFound());
	}

	/**
	 * Test method for
	 * {@link com.ricgonmen.ms_user.rest.controller.MsUserController#nuevousuario(com.ricgonmen.ms_user.rest.dto.CreateUserDTO)}.
	 */
	@Test
	final void testNuevousuario() throws Exception {
		CreateUserDTO createUserDTO = new CreateUserDTO("username_", "name_", "email_", Gender.MALE, "picture_");
		UserDTO userDTO = new UserDTO(Long.valueOf(1), "username_", "name_", "email_", Gender.MALE, "picture_");

		Mockito.when(msUserService.addUser(createUserDTO)).thenReturn(userDTO);

		String inputJson = mapToJson(createUserDTO);

		mockMvc.perform(post("/api/user").contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson))
				.andExpect(status().isCreated()).andExpect(jsonPath("$.id", Matchers.is(1)));
	}

	/**
	 * Test method for
	 * {@link com.ricgonmen.ms_user.rest.controller.MsUserController#editarusuarioPorUsername(com.ricgonmen.ms_user.rest.dto.UserDTO, java.lang.String)}.
	 */
	@Test
	final void testEditarusuarioPorUsernameExistente() throws Exception {
		UserDTO userDTO = new UserDTO(Long.valueOf(1), "username_", "name_", "email_", Gender.MALE, "picture_");

		Mockito.when(msUserService.updateUser(userDTO, "username_")).thenReturn(userDTO);

		String inputJson = mapToJson(userDTO);

		mockMvc.perform(put("/api/user/username_").contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson))
				.andExpect(status().isOk()).andExpect(jsonPath("$.id", Matchers.is(1)));
	}

	/**
	 * Test method for
	 * {@link com.ricgonmen.ms_user.rest.controller.MsUserController#editarusuarioPorUsername(com.ricgonmen.ms_user.rest.dto.UserDTO, java.lang.String)}.
	 */
	@Test
	final void testEditarusuarioPorUsernameNoExistente() throws Exception {
		UserDTO userDTO = new UserDTO(Long.valueOf(1), "username_", "name_", "email_", Gender.MALE, "picture_");

		Mockito.when(msUserService.updateUser(userDTO, "username_")).thenThrow(new UserNotFoundException("username_"));

		String inputJson = mapToJson(userDTO);

		mockMvc.perform(put("/api/user/username_").contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson))
				.andExpect(status().isNotFound());
	}

	/**
	 * Test method for
	 * {@link com.ricgonmen.ms_user.rest.controller.MsUserController#borrarusuarioPorUsername(java.lang.String)}.
	 */
	@Test
	final void testBorrarusuarioExistentePorUsername() throws Exception {
		UserDTO userDTO = new UserDTO(Long.valueOf(1), "username_", "name_", "email_", Gender.MALE, "picture_");

		Mockito.when(msUserService.deleteUser("username_")).thenReturn(userDTO);

		String inputJson = mapToJson(userDTO);

		mockMvc.perform(delete("/api/user/username_").contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson))
				.andExpect(status().isOk());
	}

	/**
	 * Test method for
	 * {@link com.ricgonmen.ms_user.rest.controller.MsUserController#borrarusuarioPorUsername(java.lang.String)}.
	 */
	@Test
	final void testBorrarusuarioNoExistentePorUsername() throws Exception {
		UserDTO userDTO = new UserDTO(Long.valueOf(1), "username_", "name_", "email_", Gender.MALE, "picture_");

		Mockito.when(msUserService.deleteUser("username_")).thenThrow(new UserNotFoundException("username_"));

		String inputJson = mapToJson(userDTO);

		mockMvc.perform(delete("/api/user/username_").contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson))
				.andExpect(status().isNotFound());
	}
}
