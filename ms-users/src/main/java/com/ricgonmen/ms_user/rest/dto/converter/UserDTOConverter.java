package com.ricgonmen.ms_user.rest.dto.converter;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.ricgonmen.ms_user.rest.dto.UserDTO;
import com.ricgonmen.ms_user.rest.model.User;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class UserDTOConverter {
	
	private final ModelMapper modelMapper;
	
	
//	@PostConstruct
//	public void init() {
//		modelMapper.addMappings(new PropertyMap<Producto, ProductoDTO>() {
//
//			@Override
//			protected void configure() {
//				map().setCategoria(source.getCategoria().getNombre());
//			}
//		});
//	}
	
	public UserDTO convertToDto(User user) {
		return modelMapper.map(user, UserDTO.class);		
	}
	
	public User convertToEntity(UserDTO userDTO) {
		return modelMapper.map(userDTO, User.class);		
	}

}