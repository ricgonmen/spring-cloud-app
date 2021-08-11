package com.ricgonmen.ms_user.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.ricgonmen.ms_user.rest.dto.CreateUserDTO;
import com.ricgonmen.ms_user.rest.dto.UserDTO;
import com.ricgonmen.ms_user.rest.model.User;


public interface MsUserService {

	public List<UserDTO> getUsers();
	
	public Page<User> getUsers(Pageable pageable);
	
	public UserDTO getUser(String username);
	
	public UserDTO addUser(CreateUserDTO newuser);

	public UserDTO updateUser(UserDTO newUserData, String username);
	
	public UserDTO getRandomUserDTO();
	
	public List<User> addRandomUsers (Long number);
	
	public void deleteUser(String username);

}
