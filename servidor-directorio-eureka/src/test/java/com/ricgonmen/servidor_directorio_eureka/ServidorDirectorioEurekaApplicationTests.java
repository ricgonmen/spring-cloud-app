package com.ricgonmen.servidor_directorio_eureka;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;


@SpringBootTest
@ActiveProfiles("test")
class ServidorDirectorioEurekaApplicationTests {
	
	@Autowired
	ServidorDirectorioEurekaApplication servidorDirectorioEurekaApplication;

	@Test
	void serverOk() {		  
	      assertNotNull(servidorDirectorioEurekaApplication);
	}

}
