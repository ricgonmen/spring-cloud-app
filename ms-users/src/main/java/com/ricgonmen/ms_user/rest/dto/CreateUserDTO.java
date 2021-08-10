/*The application to develop has to manage a collection of users with the following information:

Username (unique)
Name
Email
Gender
Picture (only URL value)

Users will be persisted to a database. You can use any database of your preference, relational or
not. Usage of a memory database or one integrated in the app itself is advised for simplicity.*/

package com.ricgonmen.ms_user.rest.dto;


import org.springframework.web.client.RestTemplate;

import com.ricgonmen.ms_user.rest.model.User;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class CreateUserDTO {

	public static UserDTO createRamdom() {
		RestTemplate restTemplate = new RestTemplate();
		RamdomUserDTO result = restTemplate.getForObject(
				"https://randomapi.com/api/198b72ae540f6e93e8e9e61949bd1fee"
				, RamdomUserDTO.class);
		
		return result.getResults().iterator().next();
	}

	private String username;
	
	private String name;
	
	private String email;
	
	private User.Gender gender;
	
	private String picture;	
}
