/**
 * 
 */
package com.ricgonmen.ms_user.rest.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

/**
 * @author ricardo
 *
 */
@ExtendWith(SpringExtension.class)
@WebMvcTest(MsUserController.class)
class MsUserControllerTest {

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	void setUp() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterEach
	void tearDown() throws Exception {
	}

	
	@MockBean
    MsUserController msUserController;
 
    @Autowired
    MockMvc mockMvc;
    
	/**
	 * Test method for {@link com.ricgonmen.ms_user.rest.controller.MsUserController#obtenerTodos()}.
	 */
	@Test
	final void testObtenerTodos() {
		/*
		mockMvc.perform(get("/user"))
        .andExpect(status().isNotFound());
        */
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link com.ricgonmen.ms_user.rest.controller.MsUserController#obtenerUnoPorUsername(java.lang.String)}.
	 */
	@Test
	final void testObtenerUnoPorUsername() {
		/*
		UserDTO user = msUserService.getRandomUserDTO();
        List<UserDTO> users = Arrays.asList(user);
 
        Mockito.when(msUserService.getUsers()).thenReturn(users);
 
        mockMvc.perform(get("/user/a"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", Matchers.hasSize(1)))
                .andExpect(jsonPath("$[0].username", Matchers.is(user.getUsername())));
                */
		fail("Not yet implemented"); // TODO
	}

	
	@Test
    public void testfindOne() throws Exception {
        
    }

	
	/**
	 * Test method for {@link com.ricgonmen.ms_user.rest.controller.MsUserController#nuevousuario(com.ricgonmen.ms_user.rest.dto.CreateUserDTO)}.
	 */
	@Test
	final void testNuevousuario() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link com.ricgonmen.ms_user.rest.controller.MsUserController#editarusuarioPorUsername(com.ricgonmen.ms_user.rest.dto.UserDTO, java.lang.String)}.
	 */
	@Test
	final void testEditarusuarioPorUsername() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link com.ricgonmen.ms_user.rest.controller.MsUserController#borrarusuarioPorUsername(java.lang.String)}.
	 */
	@Test
	final void testBorrarusuarioPorUsername() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link com.ricgonmen.ms_user.rest.controller.MsUserController#generarUsuariosRandom(java.lang.Long)}.
	 */
	@Test
	final void testGenerarUsuariosRandom() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link com.ricgonmen.ms_user.rest.controller.MsUserController#MsUserController()}.
	 */
	@Test
	final void testMsUserController() {
		fail("Not yet implemented"); // TODO
	}

}
