package com.ricgonmen.ms_user.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
public class UserDTO {
	private Long id;
	
	private String username;
	
	private String name;
	
	private String email;
	
	private String gender;
	
	private String picture;	
}
