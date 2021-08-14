package com.ricgonmen.ms_user;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.ricgonmen.ms_user.rest.controller.MsUserController;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class MsUserApplicationTest {

	@Autowired
	MsUserController msUserController;

	@Test
	void contextLoads() {
		Assertions.assertNotNull(msUserController);
	}
}
