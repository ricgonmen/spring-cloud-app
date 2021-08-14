package com.ricgonmen.ms_user.dto;

import com.ricgonmen.ms_user.model.User.Gender;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @NoArgsConstructor @ToString @EqualsAndHashCode(callSuper=true)
public class UserDTO extends CreateUserDTO {
	
	private Long id;
	
	public UserDTO(Long id, String username, String name, String email, Gender gender, String picture) {
		super(username, name, email, gender, picture);
		this.id = id;
	}
	
}
