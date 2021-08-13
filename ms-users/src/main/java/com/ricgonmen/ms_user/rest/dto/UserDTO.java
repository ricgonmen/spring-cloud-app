package com.ricgonmen.ms_user.rest.dto;

import com.ricgonmen.ms_user.rest.model.User.Gender;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @NoArgsConstructor @ToString
public class UserDTO extends CreateUserDTO {
	
	private Long id;
	
	public UserDTO(Long id, String username, String name, String email, Gender gender, String picture) {
		super(username, name, email, gender, picture);
		this.id = id;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserDTO other = (UserDTO) obj;
		if (getEmail() == null) {
			if (other.getEmail() != null)
				return false;
		} else if (!getEmail().equals(other.getEmail()))
			return false;
		if (getGender() != other.getGender())
			return false;
		if (getName() == null) {
			if (other.getName() != null)
				return false;
		} else if (!getName().equals(other.getName()))
			return false;
		if (getPicture() == null) {
			if (other.getPicture() != null)
				return false;
		} else if (!getPicture().equals(other.getPicture()))
			return false;
		if (getUsername() == null) {
			if (other.getUsername() != null)
				return false;
		} else if (!getUsername().equals(other.getUsername()))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}	
}
