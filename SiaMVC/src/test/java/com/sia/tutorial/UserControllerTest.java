package com.sia.tutorial;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.atLeastOnce;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;

import org.junit.Test;

import com.sia.tutorial.data.UserRepository;
import com.sia.tutorial.web.UserController;

import org.springframework.test.web.servlet.MockMvc;

public class UserControllerTest {

	@Test
	public void shouldShowRegistration() throws Exception {
		UserController controller = new UserController();
		MockMvc mockMvc = standaloneSetup(controller).build();
		mockMvc.perform(get("/user/register")).andExpect(view().name("registerForm"));
	}

	@Test
	public void shouldProcessRegistration() throws Exception {

		UserRepository mockRepository = mock(UserRepository.class);
		User unsaved = new User("olegbezk", "24hours", "Oleg", "Bezk", "some@email.com");
		User saved = new User(24L, "olegbezk", "24hours", "Oleg", "Bezk", "some@email.com");
		when(mockRepository.save(unsaved)).thenReturn(saved);

		UserController controller = new UserController(mockRepository);
		MockMvc mockMvc = standaloneSetup(controller).build();

		mockMvc.perform(post("/user/register")
				.param("firstName", "Oleg")
				.param("lastName", "Bezk")
				.param("username", "olegbezk")
				.param("password", "24hours")
				.param("email", "some@email.com"))
				.andExpect(redirectedUrl("/user/olegbezk"));

		verify(mockRepository, atLeastOnce()).save(unsaved);
	}

}
