package com.example.ricgonmen.cliente_servconf_eureka;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import lombok.Getter;
import lombok.Setter;

@Component
@ConfigurationProperties(prefix="some")
@Getter 
@Setter
public class ClienteServconfEurekaConfiguracion {
	private String property;
}
