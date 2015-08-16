package com.sia.tutorial.web;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sia.tutorial.User;
import com.sia.tutorial.data.UserRepository;

@Controller
@RequestMapping("/user")
public class UserController {

	private UserRepository userRepository;

	public UserController() {
	}

	@Autowired
	public UserController(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@RequestMapping(value = "/register", method = GET)
	public String showRegistrationForm() {
		return "registerForm";
	}

	@RequestMapping(value = "/register", method = POST)
	public String processRegistration(@Valid User user, Errors errors) {
		if (errors.hasErrors()) {
			return "registerForm";
		}
		userRepository.save(user);
		return "redirect:/user/" + user.getUsername();
	}

	@RequestMapping(value = "/{username}", method = GET)
	public String showUserProfile(@PathVariable String username, Model model) {
		User user = userRepository.findByUsername(username);
		model.addAttribute(user);
		return "profile";
	}

}
