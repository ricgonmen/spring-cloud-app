package com.ricgonmen.ms_user.dto.converter;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.ricgonmen.ms_user.dto.UserDTO;
import com.ricgonmen.ms_user.model.User;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class UserDTOConverter {
	
	private final ModelMapper modelMapper;
	
	public UserDTO convertToDto(User user) {
		return modelMapper.map(user, UserDTO.class);		
	}
	
	public User convertToEntity(UserDTO userDTO) {
		return  modelMapper.map(userDTO, User.class);		
	}
}