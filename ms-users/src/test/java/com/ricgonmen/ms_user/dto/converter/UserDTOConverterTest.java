package com.ricgonmen.ms_user.dto.converter;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.ricgonmen.ms_user.dto.UserDTO;
import com.ricgonmen.ms_user.model.User;
import com.ricgonmen.ms_user.model.User.Gender;

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
