package com.ricgonmen.ms_user.service;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.ricgonmen.ms_user.dto.UserDTO;
import com.ricgonmen.ms_user.dto.converter.UserDTOConverter;
import com.ricgonmen.ms_user.model.UserRepository;
import com.ricgonmen.ms_user.service.impl.MsUserServiceImpl;

@ExtendWith(SpringExtension.class)
class MsUserServiceTest {


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
    void testRandomUserGenerator() throws Exception {
    	UserDTO randomUserDTO = msUserService.getRandomUserDTO();
    	assertNotNull(randomUserDTO);
    }

}
