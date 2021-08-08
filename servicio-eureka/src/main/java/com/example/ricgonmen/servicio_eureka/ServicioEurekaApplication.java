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

package com.example.ricgonmen.servicio_eureka;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

@EnableFeignClients
@EnableDiscoveryClient
@SpringBootApplication
@RestController
public class ServicioEurekaApplication {

	@Value("${eureka.instance.instance-id}")
	private String instancia;

	@Value("${server.port}")
	private int puerto;
	
	public static void main(String[] args) {
		SpringApplication.run(ServicioEurekaApplication.class, args);
	}

	@RequestMapping("/")
	public String mensaje() {
		return "Un saludo desde la instancia " + instancia + " en el puerto " + puerto;
	}
}
