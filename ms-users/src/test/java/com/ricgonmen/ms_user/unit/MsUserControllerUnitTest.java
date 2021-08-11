package com.ricgonmen.ms_user.unit;

import static org.junit.jupiter.api.Assertions.assertNotNull;

// https://howtodoinjava.com/spring-boot2/testing/spring-boot-2-junit-5/
// https://github.com/javaquery/spring-boot-examples

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
 
import java.util.Arrays;
import java.util.List;
 
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import com.ricgonmen.ms_user.rest.controller.MsUserController;
import com.ricgonmen.ms_user.rest.dto.UserDTO;
import com.ricgonmen.ms_user.service.MsUserService;

import lombok.extern.slf4j.Slf4j;
 
@ExtendWith(SpringExtension.class)
@WebMvcTest(MsUserController.class)
@Slf4j
public class MsUserControllerUnitTest {
 
    @MockBean
    MsUserService msUserService;
 
    @Autowired
    MockMvc mockMvc;
    
    /*
    @Test
    public void testRandomUserGenerator() throws Exception {
    	UserDTO randomUserDTO = msUserService.getRandomUserDTO();
    	assertNotNull(randomUserDTO);
    }
 
    @Test
    public void testfindAll() throws Exception {
        UserDTO user = msUserService.getRandomUserDTO();
        List<UserDTO> users = Arrays.asList(user);
 
        Mockito.when(msUserService.getUsers()).thenReturn(users);
 
        mockMvc.perform(get("/users"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", Matchers.hasSize(1)))
                .andExpect(jsonPath("$[0].username", Matchers.is(user.getUsername())));
    }
    */
    
    @Test
    public void testfindOne() throws Exception {
    	log.info("*** testfindOne");
    	log.info("*** mockMvc: " + mockMvc);
    	log.info("*** mockMvc: " + mockMvc.toString());
        ResultActions result = mockMvc.perform(get("/users/a"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", Matchers.hasSize(1)))
                .andExpect(jsonPath("$[0].username", Matchers.is("a")));
    }
 
}