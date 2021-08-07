/**
 * In order to create our client, we're going to again start off at start. spring. io. 
 * And we're going to give it a group ID, the same one, so io. shultz. dustin, and we'll 
 * make the Artifact client. And it will have the exact same dependencies that we used in 
 * the application service. So we want Eureka Discovery, want DevTools, and we want the 
 * Actuator. Click Generate Project
 */
package com.example.ricgonmen.clienteeureka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;

@EnableDiscoveryClient
@SpringBootApplication
@RestController
public class ClienteEurekaApplication {
	
	@Autowired
	private EurekaClient cliente;
	@Autowired
	private RestTemplateBuilder constructorPlantillaRest;	

	public static void main(String[] args) {
		SpringApplication.run(ClienteEurekaApplication.class, args);
	}

	/**
	 * So the EurekaClient is calling out to the Discovery Server, and it's getting information 
	 * about a service ID called service, and it's returning it back to us as an instanceInfo. 
	 * And then from that instanceInfo, we're getting the HomePageUrl, which is the base URL of 
	 * our service, and then we're using our restTemplate to call that service, specifically a 
	 * GET on that service, and it returns a string back to us. 
	 * @return
	 */
	@RequestMapping("/")
	public String llamaServicio() {
		RestTemplate plantillaRest = constructorPlantillaRest.build();
		InstanceInfo informacionInstancia = cliente.getNextServerFromEureka("servicio-eureka", false);
		String baseURL = informacionInstancia.getHomePageUrl();
		ResponseEntity<String> respuesta = plantillaRest.exchange(baseURL,HttpMethod.GET,null, String.class);  
		return respuesta.getBody();
	}
}
