/*
 * Remember that we're diving into each of the components of service discovery, and the next 
 * on our list is the application service. This is whatever is providing the functionality. 
 * It's the thing that's receiving the requests from clients and returning responses. And it's 
 * a dependency of other services. So other services depend on its functionality to perform 
 * their functionality. You would typically run one or more instances of the application service. 
 * The application service is a user of the discovery client. It's going to use that client to 
 * call out to the Discovery Server and register and deregister itself. Just like we did for 
 * the Service Discovery 
 */

package com.ricgonmen.ms_user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@EnableDiscoveryClient
@SpringBootApplication
public class MsUserApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(MsUserApplication.class, args);
	}

}
