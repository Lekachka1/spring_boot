package com.example.springboot.controller;

import com.example.springboot.model.User;
import com.example.springboot.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping({"/", "/users"})
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping({"", "/", "us"})
    public String showAllUsers(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        return "us";
    }

    @GetMapping(value = "/new")
    public String addUserForm(@ModelAttribute("user") User user) {
        return "form";
    }

    @GetMapping("/{id}/edit")
    public String editUser(Model model, @PathVariable("id") int id) {
        model.addAttribute("user", userService.readUser(id));
        return "edit";
    }

    @PostMapping()
    public String saveUser(@ModelAttribute("user") @Valid User user, BindingResult bindingResult,
                           RedirectAttributes attributes) {
        if (bindingResult.hasErrors()) {
            return "form";
        }

        if (user.getId() != 0) {
            User existingUser = userService.readUser(user.getId());
            if (existingUser != null) {
                userService.updateUser(user);
            } else {
                userService.saveUser(user);
            }
        } else {
            userService.saveUser(user);
        }

        attributes.addFlashAttribute("flashMessage",
                "Пользователь " + user.getFirstName() + " создан");
        return "redirect:/users";
    }

    @PatchMapping("/{id}")
    public String updateUser(@ModelAttribute("user") @Valid User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "edit";
        }
        userService.updateUser(user);
        return "redirect:/";
    }


    @DeleteMapping("/delete")
    public String deleteUser(@RequestParam("id") int id) {
        User user = userService.readUser(id);
        if (user == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Пользователь с ID " + id + " не найден");
        }

        userService.deleteUser(id);

        return "redirect:/users";
    }
}