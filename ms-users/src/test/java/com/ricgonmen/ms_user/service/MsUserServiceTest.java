/**
 * 
 */
package com.ricgonmen.ms_user.service;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.ricgonmen.ms_user.rest.dto.UserDTO;
import com.ricgonmen.ms_user.rest.dto.converter.UserDTOConverter;
import com.ricgonmen.ms_user.rest.model.UserRepository;
import com.ricgonmen.ms_user.service.impl.MsUserServiceImpl;

/**
 * @author ricardo
 *
 */
@ExtendWith(SpringExtension.class)
class MsUserServiceTest {

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

	@TestConfiguration
    static class MsUserServiceUnitTestContextConfiguration {
 
        @Bean
        public MsUserService msUserService() {
            return new MsUserServiceImpl();
        }
    }
 
	@Autowired
    MsUserService msUserService;
 
    @MockBean
    private UserRepository usuarioRepositorio;
    @MockBean
	private UserDTOConverter usuarioDTOConverter;
    
    @Test
    public void testRandomUserGenerator() throws Exception {
    	UserDTO randomUserDTO = msUserService.getRandomUserDTO();
    	assertNotNull(randomUserDTO);
    }
    
	/**
	 * Test method for {@link com.ricgonmen.ms_user.service.MsUserService#getUsers()}.
	 */
	@Test
	final void testGetUsers() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link com.ricgonmen.ms_user.service.MsUserService#getUser(java.lang.String)}.
	 */
	@Test
	final void testGetUser() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link com.ricgonmen.ms_user.service.MsUserService#addUser(com.ricgonmen.ms_user.rest.dto.CreateUserDTO)}.
	 */
	@Test
	final void testAddUser() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link com.ricgonmen.ms_user.service.MsUserService#updateUser(com.ricgonmen.ms_user.rest.dto.UserDTO, java.lang.String)}.
	 */
	@Test
	final void testUpdateUser() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link com.ricgonmen.ms_user.service.MsUserService#addRandomUsers(java.lang.Long)}.
	 */
	@Test
	final void testAddRandomUsers() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link com.ricgonmen.ms_user.service.MsUserService#deleteUser(java.lang.String)}.
	 */
	@Test
	final void testDeleteUser() {
		fail("Not yet implemented"); // TODO
	}

}
