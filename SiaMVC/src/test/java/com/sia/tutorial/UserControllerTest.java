package com.sia.tutorial;

import com.sia.tutorial.data.UserRepository;
import com.sia.tutorial.web.UserController;
import org.junit.Test;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

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

	@Test
	public void shouldFailValidationWithNoData() throws Exception {
		UserRepository mockRepository = mock(UserRepository.class);
		UserController controller = new UserController(mockRepository);
		MockMvc mockMvc = standaloneSetup(controller).build();

		mockMvc.perform(post("/user/register"))
				.andExpect(status().isOk())
				.andExpect(view().name("registerForm"))
				.andExpect(model().errorCount(5))
				.andExpect(model().attributeHasFieldErrors(
						"user", "firstName", "lastName", "username", "password", "email"));
	}

}
