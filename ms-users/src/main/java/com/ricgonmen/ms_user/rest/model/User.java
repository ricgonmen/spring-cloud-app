/*The application to develop has to manage a collection of users with the following information:

Username (unique)
Name
Email
Gender
Picture (only URL value)

Users will be persisted to a database. You can use any database of your preference, relational or
not. Usage of a memory database or one integrated in the app itself is advised for simplicity.*/

package com.ricgonmen.ms_user.rest.model;

import java.util.Random;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor
@Entity
@Table(name = "tusers")
public class User {
	
	private String randomString() {
	    int leftLimit = 97; // letter 'a'
	    int rightLimit = 122; // letter 'z'
	    int targetStringLength = 10;
	    Random random = new Random();

	    String generatedString = random.ints(leftLimit, rightLimit + 1)
	      .limit(targetStringLength)
	      .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
	      .toString();

	    return generatedString;
	}

	public User(boolean random) {
		super();
		if (random) {
			this.id = null;
			this.username = randomString();
			this.name = randomString();
			this.email = randomString();
			this.gender = randomString();
			this.picture = randomString();
		}
	}


	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY) 
	private Long id;
	
	@Column(unique=true)
	@NotNull
	private String username;
	
	private String name;
	
	private String email;
	
	private String gender;
	
	private String picture;
	
}
