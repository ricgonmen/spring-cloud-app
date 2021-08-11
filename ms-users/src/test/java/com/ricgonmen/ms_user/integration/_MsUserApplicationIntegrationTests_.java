package com.ricgonmen.ms_user.integration;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.ricgonmen.ms_user.rest.controller.MsUserController;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class _MsUserApplicationIntegrationTests_ {
	
	@Autowired
	private MsUserController controller;
	
	/**
	 * @throws Exception if it has not been successfully injected
	 */
	@Test
	public void contextLoads() throws Exception {
		assertThat(controller).isNotNull();
	}

}
