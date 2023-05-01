package dev.alphacodez.marketplace.auth;

import dev.alphacodez.marketplace.config.security.JwtService;
import dev.alphacodez.marketplace.users.Role;
import dev.alphacodez.marketplace.users.User;
import dev.alphacodez.marketplace.users.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@RequiredArgsConstructor
@Service
public class AuthenticationService {
    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthenticationResponse createUser(RegistrationEntity request) {
;
        User user = new User(request.getName(), request.getEmail(),
                passwordEncoder.encode(request.getPassword()), request.getPhone(),Role.USER );
        repository.save(user);
        String jwtToken = jwtService.generateToken(user);

        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

    public AuthenticationResponse authenticateUser(AuthenticationEntity request) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    request.getEmail(), request.getPassword()
            ));
            System.out.println("Passed");

        } catch (Exception e) {
            System.out.println(e);
            throw new IllegalStateException("Exception Occured");
        }

        var user = repository.findByEmail(request.getEmail())
                .orElseThrow(() -> new UsernameNotFoundException("User does not exist"));

        String jwtToken = jwtService.generateToken(user);

        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

    public boolean validateToken(TokenAuth request) {
        String token = request.getToken();
        System.out.println("Getting ContextHolder: .....");
        System.out.println(SecurityContextHolder.getContext().getAuthentication());
        if (SecurityContextHolder.getContext().getAuthentication() != null) {

            String username = SecurityContextHolder.getContext().getAuthentication().getName();
            String testUsername = jwtService.extractUsername(token);
            return username.equals(testUsername);
        } else {
            throw new IllegalStateException("User has no session!");
        }


    }
}
