package com.sia.tutorial.web;

import com.sia.tutorial.User;
import com.sia.tutorial.data.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;

import java.io.File;
import java.io.IOException;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

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
    public String showRegistrationForm(Model model) {
        model.addAttribute(new User());
        return "registerForm";
    }

    @RequestMapping(value = "/register", method = POST)
    public String processingRegistration(@Valid RegisterForm registerForm,
                                         Errors errors) throws IOException, IllegalStateException {
        if (errors.hasErrors()) {
            return "registerForm";
        }
        User user = registerForm.toUser();
        userRepository.save(user);

//        MultipartFile profilePicture = registerForm.getProfilePicture();
//        profilePicture.transferTo(new File("/" + user.getUsername() + ".jpg"));

        return "redirect:/user/" + user.getUsername();
    }

    @RequestMapping(value="/me", method=GET)
    public String me() {
        System.out.println("ME ME ME ME ME ME ME ME ME ME ME");
        return "home";
    }

    @RequestMapping(value = "/{username}", method = GET)
    public String showUserProfile(@PathVariable String username, Model model) {
        User user = userRepository.findByUsername(username);
        model.addAttribute(user);
        return "profile";
    }

}
