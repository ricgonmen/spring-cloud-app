package com.ricgonmen.ms_user.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.ricgonmen.ms_user.rest.dto.CreateUserDTO;
import com.ricgonmen.ms_user.rest.dto.RamdomUserDTO;
import com.ricgonmen.ms_user.rest.dto.UserDTO;
import com.ricgonmen.ms_user.rest.dto.converter.UserDTOConverter;
import com.ricgonmen.ms_user.rest.excepcion.UserNotFoundException;
import com.ricgonmen.ms_user.rest.model.User;
import com.ricgonmen.ms_user.rest.model.UserRepository;

@Service
public class MsUserService {
	@Autowired
	private UserRepository usuarioRepositorio;
	@Autowired
	private UserDTOConverter usuarioDTOConverter;
	
	public List<UserDTO> getUsers() {
		List<User> usuarios = usuarioRepositorio.findAll();
		
		List<UserDTO> dtoResult = usuarios.stream().map(usuarioDTOConverter::convertToDto)
				.collect(Collectors.toList());
		
		return dtoResult;
	}
	
	public UserDTO getUser(String username) {
		return usuarioDTOConverter.convertToDto(usuarioRepositorio.findByUsername(username).orElseThrow(() -> new UserNotFoundException(username)));
	}
	
	public UserDTO addUser(CreateUserDTO newuser) {
		User result = usuarioRepositorio.save(new User(newuser));
		return usuarioDTOConverter.convertToDto(result);
	}

	public UserDTO updateUser(UserDTO newUserData, String username) {
		User currentUser = usuarioRepositorio.findByUsername(username).orElseThrow(() -> new UserNotFoundException(username));

		if (currentUser != null) {
			newUserData.setId(currentUser.getId());
			usuarioRepositorio.save(usuarioDTOConverter.convertToEntity(newUserData));			
			return newUserData;
		} else {
			return null;
		}
	}
	
	public UserDTO getRandomUserDTO() {
		RestTemplate restTemplate = new RestTemplate();
		RamdomUserDTO randomUserDTO = restTemplate.getForObject(
				"https://randomapi.com/api/198b72ae540f6e93e8e9e61949bd1fee"
				, RamdomUserDTO.class);
		return randomUserDTO.getResults().iterator().next();
	}
	
	public List<User> addRandomUsers (Long number) {
		List<User> result = new ArrayList<>();
		User randomUser;
		
		for (int i=0;i<number;i++) {
			randomUser = usuarioDTOConverter.convertToEntity(getRandomUserDTO());
			result.add(randomUser);
			usuarioRepositorio.save(randomUser);
		}
		
		return result;
	}

	public void deleteUser(String username) {
		User user = usuarioRepositorio.findByUsername(username).orElseThrow(() -> new UserNotFoundException(username));
		usuarioRepositorio.deleteById(user.getId());
	}

}
