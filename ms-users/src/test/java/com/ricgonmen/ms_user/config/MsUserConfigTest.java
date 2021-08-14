package com.ricgonmen.ms_user.config;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.context.WebApplicationContext;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class MsUserConfigTest {

	@Autowired
	private WebApplicationContext webAppContext;

	@Test
	void contextLoads() {
	    assertNotNull(webAppContext);
	}

}
