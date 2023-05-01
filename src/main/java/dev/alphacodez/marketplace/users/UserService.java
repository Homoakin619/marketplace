package dev.alphacodez.marketplace.users;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;

    public boolean comparePasswords(String username,String password) {
        User user = repository.findByEmail(username)
                .orElseThrow(()-> new IllegalStateException("User not found"));
        return passwordEncoder.matches(password,user.getPassword());
    }
    @Transactional
    public void updateUser(String username,String oldPassword, String newPassword) {
        User user = repository.findByEmail(username)
                .orElseThrow(()-> new IllegalStateException("User not found"));
        if(comparePasswords(username,oldPassword)) {
            user.setPassword(passwordEncoder.encode(newPassword));
        }else {
            throw new IllegalStateException("Passwords do not match");
        }
    }
}
