package br.com.laisbarbosa.logincompleto.user.controller;

import br.com.laisbarbosa.logincompleto.user.model.Role;
import br.com.laisbarbosa.logincompleto.user.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final UserService userService;

    public AdminController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String users(Model model) {
        model.addAttribute("users", userService.findAll());
        model.addAttribute("roles", Role.values());
        return "dashboard/admin";
    }

    @PostMapping("/users/{id}/role")
    public String changeRole(@PathVariable String id, @RequestParam Role role, Principal principal) {
        if (!userService.findById(id).getEmail().equals(principal.getName())) {
            userService.changeRole(id, role);
        }
        return "redirect:/admin";
    }

    @PostMapping("/users/{id}/toggle")
    public String toggle(@PathVariable String id, Principal principal) {
        if (!userService.findById(id).getEmail().equals(principal.getName())) {
            userService.toggleEnabled(id);
        }
        return "redirect:/admin";
    }
}