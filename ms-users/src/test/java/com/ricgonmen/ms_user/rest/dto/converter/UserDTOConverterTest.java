package com.ricgonmen.ms_user.rest.dto.converter;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.client.AutoConfigureWebClient;
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import com.ricgonmen.ms_user.rest.config.MsUserConfig;

import com.ricgonmen.ms_user.rest.dto.UserDTO;
import com.ricgonmen.ms_user.rest.model.User;
import com.ricgonmen.ms_user.rest.model.User.Gender;

@SpringBootTest
class UserDTOConverterTest {
	
	@Autowired
	private UserDTOConverter userDTOConverter;
	
	@Test
	final void testConvertToDto() {
		UserDTO userDTO = new UserDTO(Long.valueOf(1),"username_","name_","email_",Gender.MALE,"picture_");
		User user = userDTOConverter.convertToEntity(userDTO);
		assertEquals(userDTO.getEmail(), user.getEmail()); 
		assertEquals(userDTO.getGender(), user.getGender());
		assertEquals(userDTO.getId(), user.getId());
		assertEquals(userDTO.getPicture(), user.getPicture());
		assertEquals(userDTO.getName(), user.getName());
		assertEquals(userDTO.getUsername(), user.getUsername());		
	}

	@Test
	final void testConvertToEntity() {
		User user = new User(Long.valueOf(1),"username_","name_","email_",Gender.MALE,"picture_");
		UserDTO userDTO =  userDTOConverter.convertToDto(user);
		assertEquals(userDTO.getEmail(), user.getEmail()); 
		assertEquals(userDTO.getGender(), user.getGender());
		assertEquals(userDTO.getId(), user.getId());
		assertEquals(userDTO.getPicture(), user.getPicture());
		assertEquals(userDTO.getName(), user.getName());
		assertEquals(userDTO.getUsername(), user.getUsername());
	}

}
