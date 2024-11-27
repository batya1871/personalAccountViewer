package ru.batievsky.personalAccountViewer.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.batievsky.personalAccountViewer.models.Account;
import ru.batievsky.personalAccountViewer.models.User;
import ru.batievsky.personalAccountViewer.services.UserService;

@Controller
@RequiredArgsConstructor
//@RequestMapping(value = "/auth")
public class UsersController {
    private final UserService userService;
    @GetMapping("/registration")
    public String getRegistration(@ModelAttribute("user") User user)
    {
        System.out.println("in get reg");
        return "registration";
    }


    @GetMapping("/login")
    public String getLogin() {
        System.out.println("in get login");
        return "login";
    }

    @PostMapping("/processing_login_request")
    public String ProcessingLoginRequest(){
        System.out.println("Login analization...");
        return "redirect:/login";
    }

    @PostMapping("/registration")
    public String createUser(User user, Model model) {
        System.out.println("in Post");
        System.out.println(user);
        if (!userService.createUser(user))
        {
            model.addAttribute("ErrorMsg", "Пользователь с email: " + user.getEmail() + " уже существует");
            return "registration";
        }
        userService.createUser(user);
        return "redirect:/login";
    }

}
