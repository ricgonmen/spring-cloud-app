package com.example.ricgonmen.servidor_gateway_eureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class ServidorGatewayEurekaApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServidorGatewayEurekaApplication.class, args);
	}

}
