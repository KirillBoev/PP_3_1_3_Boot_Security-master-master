package ru.kata.spring.boot_security.demo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.kata.spring.boot_security.demo.entities.User;
import ru.kata.spring.boot_security.demo.services.UserService;

import java.security.Principal;

@Controller
@RequestMapping("/admin")

public class AdminController {
    private final UserService userService;

    public AdminController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/adminview")
    public String showAllUsers(Principal principal, Model model) {
        model.addAttribute("user", userService.getUserByEmail(principal.getName()));
        return "adminview";
    }

    @GetMapping("/new")
    public String addNewUser(@ModelAttribute User user, Model model, Principal principal) {
        model.addAttribute("admin", userService.getUserByEmail(principal.getName()));
        model.addAttribute("listRoles", userService.listRoles());
        return "new";
    }

    @PostMapping
    public String saveUser(@ModelAttribute User user) {
        userService.save(user);
        return "redirect:/admin";
    }

    @GetMapping("/update")
    public String updateUser(@PathVariable Long id, Model model) {
        model.addAttribute("users", userService.getUser(id));
        model.addAttribute("listRoles", userService.listRoles());
        return "redirect:/admin";
    }

    @PatchMapping("/{id}/update")
    public String saveUpdateUser(@ModelAttribute User user, Model model) {
        model.addAttribute("listRoles", userService.listRoles());
        userService.update(user);
        return "redirect:/admin";
    }

    @DeleteMapping("/{id}/delete")
    public String deleteUser(@PathVariable Long id) {
        userService.delete(id);
        return "redirect:/admin";
    }
}