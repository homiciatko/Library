package com.homik.controller;

import com.homik.config.SecurityConfig;
import com.homik.dao.UserDao;
import com.homik.model.User;
import com.homik.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MainController {

    @Autowired
    private UserDao userDao;

    @Autowired
    private EmailService emailService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String getMainPage() {

        return "main";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String getLoginPage() {

        return "login";
    }

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String getRegisterPage() {

        return "register";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String registerUser(@ModelAttribute User user) {

        try {

            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(SecurityConfig.PASSWORD_STRENGHT);
            String encodedPassword = encoder.encode(user.getPassword());

            user.setPassword(encodedPassword);
            user = userDao.save(user);
        } catch (Exception e) {
            return "redirect:/register";
        }

//        emailService.sendEmail("spring.szkolenie.test@gmail.com", user.getEmail(), "Welcome in our app", "Welcome " + user.getFirstName());

//        return "redirect:/";
        return "redirect:/login";
    }


}
