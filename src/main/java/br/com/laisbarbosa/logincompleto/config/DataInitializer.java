package br.com.laisbarbosa.logincompleto.config;

import br.com.laisbarbosa.logincompleto.user.model.Role;
import br.com.laisbarbosa.logincompleto.user.model.User;
import br.com.laisbarbosa.logincompleto.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Set;

@Configuration
public class DataInitializer {

    @Bean
    CommandLineRunner seedAdmin(UserRepository repo, PasswordEncoder encoder,
                                @Value("${app.admin.email}") String email,
                                @Value("${app.admin.password}") String password) {
        return args -> {
            if (password.isBlank() || repo.existsByEmail(email)) {
                return;
            }
            User admin = new User();
            admin.setName("Administrador");
            admin.setEmail(email);
            admin.setPassword(encoder.encode(password));
            admin.setRoles(Set.of(Role.ROLE_ADMIN));
            repo.save(admin);
        };
    }
}