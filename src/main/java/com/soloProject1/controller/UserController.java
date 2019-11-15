package com.soloProject1.controller;

import com.soloProject1.model.User;
import com.soloProject1.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/")
public class UserController {
    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    /*таблица всех юзеров*/
    @GetMapping("/admin")
    public String adminPage(Model model) {
        List<User> users = userService.readUsers();
        model.addAttribute("users", users);

        return "admin";
    }

    /*Страница добавления юзера*/
    @GetMapping("/signup")
    public String createPage(User user, Model model) {
        model.addAttribute("user", user);
        return "create";
    }

    /*метод добавление юзера*/
    @PostMapping("/addUser")
    @Transactional
    public String createUser(User user, Model model) {
        userService.addUser(user);
        model.addAttribute("users", userService.readUsers());
        return "admin";
    }

    /*Страница изменения юзера*/
    @GetMapping("/edit/{id}")
    public String editPage(@PathVariable("id") int id, Model model) {
        User user = userService.findUserById(id);
        model.addAttribute("user", user);

        return "edit";
    }

    /*Метод изменения Юзера*/
    @PostMapping("/update/{id}")
    @Transactional
    public String editUser(@PathVariable("id") int id, Model model, User user) {
        userService.editUser(user);
        model.addAttribute("users", userService.readUsers());
        return "redirect:/admin";
    }

    /*Метод Удаления Юзера*/
    @GetMapping("/delete/{id}")
    @Transactional
    public String deleteUser(@PathVariable("id") int id, Model model) {
        User user = userService.findUserById(id);
        userService.deleteUser(user);
        model.addAttribute("users", userService.readUsers());

        return "redirect:/admin";
    }

    /*Страница логина*/
    @GetMapping("/login")
    public String login(Model model, String error, String logout) {
        if (error != null)
            model.addAttribute("error", "Your username and password is invalid.");

        if (logout != null)
            model.addAttribute("message", "You have been logged out successfully.");

        return "login";
    }
}
