package com.example.ricgonmen.servidor_configuracion_eureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.config.server.EnableConfigServer;

@SpringBootApplication
@EnableConfigServer
@EnableDiscoveryClient
public class ServidorConfiguracionEurekaApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServidorConfiguracionEurekaApplication.class, args);
	}

}
