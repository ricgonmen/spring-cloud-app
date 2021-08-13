/*The application to develop has to manage a collection of users with the following information:

Username (unique)
Name
Email
Gender
Picture (only URL value)

Users will be persisted to a database. You can use any database of your preference, relational or
not. Usage of a memory database or one integrated in the app itself is advised for simplicity.

https://randomapi.com/api/baxwustz?key=NF82-NELB-0LA0-0CBM

*/

package com.ricgonmen.ms_user.rest.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.ricgonmen.ms_user.rest.dto.CreateUserDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data @AllArgsConstructor @NoArgsConstructor
@Entity
@Table(name = "tusers")
@ToString
public class User {
	public User(CreateUserDTO nuevo) {
		super();
		this.username = nuevo.getUsername();
		this.name = nuevo.getName();
		this.email = nuevo.getEmail();
		this.gender = nuevo.getGender();
		this.picture = nuevo.getPicture();
	}

	public enum Gender { MALE, FEMALE }
	
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY) 
	private Long id;
	
	@Column(unique=true)
	@NotNull
	private String username;
	
	private String name;
	
	private String email;
	
	@Enumerated(EnumType.STRING)
	private Gender gender;
		
	private String picture;
	
}
