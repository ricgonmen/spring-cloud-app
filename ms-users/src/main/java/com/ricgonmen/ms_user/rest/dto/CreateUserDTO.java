/*The application to develop has to manage a collection of users with the following information:

Username (unique)
Name
Email
Gender
Picture (only URL value)

Users will be persisted to a database. You can use any database of your preference, relational or
not. Usage of a memory database or one integrated in the app itself is advised for simplicity.*/

package com.ricgonmen.ms_user.rest.dto;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import com.ricgonmen.ms_user.rest.model.User;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class CreateUserDTO {
	
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

	public CreateUserDTO(boolean random) {
		super();
		if (random) {
			this.username = randomString();
			this.name = randomString();
			this.email = randomString();
			this.picture = randomString();
			
			User.Gender[] genders = User.Gender.values();
			this.gender = genders[ThreadLocalRandom.current().nextInt(0, genders.length)];
		}
	}


	private String username;
	
	private String name;
	
	private String email;
	
	private User.Gender gender;
	
	private String picture;	
}
