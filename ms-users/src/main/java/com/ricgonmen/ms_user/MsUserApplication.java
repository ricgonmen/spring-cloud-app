/*
 * 
 	The application will provide the following JSON web services:
	/api/user/ (GET): return the list of all users.
	/api/user/{username}/ (GET): return a single user.
	/api/user/ (POST): create a user.
	/api/user/{username}/ (PUT): update the information of a single user.
	/api/user/{username}/ (DELETE): delete a single user.
	/api/user/generate/{number}/ (GET): generate a number, provided as a parameter, of random users.
		To create the users you have to use the Random User Generator service 
		(https://randomapi.com/api/baxwustz?key=NF82-NELB-0LA0-0CBM). Users
		will be added to the collection of existing users.

Extra optional features (only if you are done with the previous features):

	- Unit tests (at least one class).
	- Pagination of the users list.
	- API documentation using Swagger, Spring REST docs or any other tool.
	
 */

package com.ricgonmen.ms_user;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import com.ricgonmen.ms_user.rest.dto.UserDTO;

import lombok.extern.slf4j.Slf4j;

@EnableFeignClients
@EnableDiscoveryClient
@SpringBootApplication
public class MsUserApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(MsUserApplication.class, args);
	}
}
