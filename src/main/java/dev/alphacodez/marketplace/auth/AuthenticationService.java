package dev.alphacodez.marketplace.auth;

import dev.alphacodez.marketplace.config.security.JwtService;
import dev.alphacodez.marketplace.exceptions.UserExistsException;
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

import java.security.Principal;


@RequiredArgsConstructor
@Service
public class AuthenticationService {
    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;


    public AuthenticationResponse createUser(RegistrationEntity request,Role role) throws UserExistsException {

        if(repository.findByEmail(request.getEmail()).isPresent())
            throw new UserExistsException("Email is taken. Kindly use another valid email");

        User user = new User(request.getName(), request.getEmail(),
                passwordEncoder.encode(request.getPassword()), request.getPhone(),role);
        repository.save(user);
        String jwtToken = jwtService.generateToken(user);

        return AuthenticationResponse.builder()
                .token(jwtToken)
                .success(true)
                .build();
    }



    public AuthenticationResponse authenticateUser(AuthenticationEntity request) {
        try {
            var auth = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    request.getEmail(), request.getPassword()
            ));
            System.out.println(auth);

        } catch (Exception e) {
            System.out.println(e);
            throw new IllegalStateException("Exception Occurred");
        }

        var user = repository.findByEmail(request.getEmail())
                .orElseThrow(() -> new UsernameNotFoundException("User does not exist"));

        String jwtToken = jwtService.generateToken(user);

        return AuthenticationResponse.builder()
                .token(jwtToken)
                .role(user.getRole())
                .success(true)
                .email(request.getEmail())
                .build();
    }

    public boolean validateToken(TokenAuth request) {
        String token = request.getToken();
        if (SecurityContextHolder.getContext().getAuthentication() != null) {

            String username = SecurityContextHolder.getContext().getAuthentication().getName();
            String testUsername = jwtService.extractUsername(token);
            return username.equals(testUsername);
        } else {
            throw new IllegalStateException("User has no session!");
        }

    }

    public void getAuthenticatedUser() throws Exception{
        if (SecurityContextHolder.getContext().getAuthentication() != null) {
            System.out.println(SecurityContextHolder.getContext());
            System.out.println(SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        } else{
            throw new IllegalAccessException("User is not authenticated");
        }
    }
}
