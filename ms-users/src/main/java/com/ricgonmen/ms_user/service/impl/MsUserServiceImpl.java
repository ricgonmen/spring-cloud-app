package com.ricgonmen.ms_user.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.ricgonmen.ms_user.dto.CreateUserDTO;
import com.ricgonmen.ms_user.dto.RamdomUserDTO;
import com.ricgonmen.ms_user.dto.UserDTO;
import com.ricgonmen.ms_user.dto.converter.UserDTOConverter;
import com.ricgonmen.ms_user.model.User;
import com.ricgonmen.ms_user.model.UserRepository;
import com.ricgonmen.ms_user.rest.excepcion.UserNotFoundException;
import com.ricgonmen.ms_user.service.MsUserService;

@Service
public class MsUserServiceImpl implements MsUserService {
	@Autowired
	private UserRepository usuarioRepositorio;
	@Autowired
	private UserDTOConverter usuarioDTOConverter;
	
	@Override
	public List<UserDTO> getUsers() {		
		return usuarioRepositorio.findAll().stream().map(usuarioDTOConverter::convertToDto)
				.collect(Collectors.toList());
	}
	
	@Override
	public UserDTO getUser(String username) {
		return usuarioDTOConverter.convertToDto(usuarioRepositorio.findByUsername(username).orElseThrow(() -> new UserNotFoundException(username)));
	}
	
	@Override
	public Page<User> getUsers(Pageable pageable) {
		return  usuarioRepositorio.findAll(pageable);
	}
	
	@Override
	public UserDTO addUser(CreateUserDTO newuser) {
		return usuarioDTOConverter.convertToDto(usuarioRepositorio.save(new User(newuser)));
	}

	@Override
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
	
	@Override
	public UserDTO getRandomUserDTO() {
		RestTemplate restTemplate = new RestTemplate();
		RamdomUserDTO randomUserDTO = restTemplate.getForObject(
				"https://randomapi.com/api/198b72ae540f6e93e8e9e61949bd1fee"
				, RamdomUserDTO.class);
		return randomUserDTO.getResults().iterator().next();
	}
	
	@Override
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

	@Override
	public UserDTO deleteUser(String username) {
		User user = usuarioRepositorio.findByUsername(username).orElseThrow(() -> new UserNotFoundException(username));
		usuarioRepositorio.deleteById(user.getId());
		return usuarioDTOConverter.convertToDto(user);
	}

}
