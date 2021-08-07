package com.example.ricgonmen.cliente_servconf_eureka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@EnableDiscoveryClient 
@RestController
@RefreshScope
public class ClienteServconfEurekaApplication {
	
	@Autowired
	private ClienteServconfEurekaConfiguracion propiedades;
	
	@Value("${some.other.property}")
	private String otraPropiedad;

	public static void main(String[] args) {
		SpringApplication.run(ClienteServconfEurekaApplication.class, args);
	}
	
	@RequestMapping
	public String printConfig() {
		StringBuilder sb = new StringBuilder();
		sb.append("'");
		sb.append(propiedades.getProperty());
		sb.append("' || '");
		sb.append(otraPropiedad);
		sb.append("'");
		
		return sb.toString();
	}
}
