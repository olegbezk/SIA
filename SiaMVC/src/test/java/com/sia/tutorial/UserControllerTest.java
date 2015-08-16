package com.sia.tutorial;

import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.Test;

import com.sia.tutorial.web.UserController;

import org.springframework.test.web.servlet.MockMvc;

public class UserControllerTest {

	@Test
	public void shouldShowRegistration() throws Exception {
		UserController controller = new UserController();
		MockMvc mockMvc = standaloneSetup(controller).build();
		mockMvc.perform(get("/user/register")).andExpect(view().name("registerForm"));
	}

}
