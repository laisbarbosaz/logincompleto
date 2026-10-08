package br.com.laisbarbosa.logincompleto.user.service;

import br.com.laisbarbosa.logincompleto.user.dto.RegisterDTO;
import br.com.laisbarbosa.logincompleto.user.model.Role;
import br.com.laisbarbosa.logincompleto.user.model.User;
import br.com.laisbarbosa.logincompleto.user.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class UserService {

    private final UserRepository repo;
    private final PasswordEncoder encoder;

    public UserService(UserRepository repo, PasswordEncoder encoder) {
        this.repo = repo;
        this.encoder = encoder;
    }

    public User register(RegisterDTO dto) {
        String email = dto.getEmail().trim().toLowerCase();
        if (repo.existsByEmail(email)) {
            throw new IllegalArgumentException("E-mail já cadastrado");
        }
        User u = new User();
        u.setName(dto.getName().trim());
        u.setEmail(email);
        u.setPassword(encoder.encode(dto.getPassword())); // hash BCrypt
        u.setRoles(Set.of(Role.ROLE_ALUNO));              // quem se cadastra sempre entra como aluno
        return repo.save(u);
    }

    public List<User> findAll() {
        return repo.findAll();
    }

    public User findById(String id) {
        return repo.findById(id).orElseThrow();
    }

    public void changeRole(String id, Role role) {
        User u = findById(id);
        u.setRoles(Set.of(role));
        repo.save(u);
    }

    public void toggleEnabled(String id) {
        User u = findById(id);
        u.setEnabled(!u.isEnabled());
        repo.save(u);
    }
}