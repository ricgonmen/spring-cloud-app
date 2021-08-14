package com.ricgonmen.ms_user.dto;

import com.ricgonmen.ms_user.model.User;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @AllArgsConstructor @NoArgsConstructor @ToString @EqualsAndHashCode
public class CreateUserDTO {

	private String username;
	
	private String name;
	
	private String email;
	
	private User.Gender gender;
	
	private String picture;
}
