package br.com.laisbarbosa.logincompleto.user.controller;

import br.com.laisbarbosa.logincompleto.user.dto.RegisterDTO;
import br.com.laisbarbosa.logincompleto.user.service.UserService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AuthController {

    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/login")
    public String login() {
        return "auth/login";
    }

    @GetMapping("/register")
    public String registerForm(Model model) {
        model.addAttribute("registerDTO", new RegisterDTO());
        return "auth/register";
    }

    @PostMapping("/register")
    public String register(@Valid @ModelAttribute("registerDTO") RegisterDTO dto,
                           BindingResult result) {
        if (dto.getPassword() != null && !dto.getPassword().equals(dto.getConfirmPassword())) {
            result.rejectValue("confirmPassword", "mismatch", "As senhas não conferem");
        }
        if (result.hasErrors()) {
            return "auth/register";
        }
        try {
            userService.register(dto);
        } catch (IllegalArgumentException e) {
            result.rejectValue("email", "duplicate", e.getMessage());
            return "auth/register";
        }
        return "redirect:/login?registered";
    }

    @GetMapping("/403")
    public String forbidden() {
        return "error/403";
    }
}