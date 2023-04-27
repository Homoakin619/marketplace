package dev.alphacodez.marketplace.auth;


import dev.alphacodez.marketplace.config.security.JwtService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
@RequestMapping("api/v1/auth")
@RestController
public class AuthenticationController {
    private final AuthenticationService service;
    private final JwtService jwtService;

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> loginUser(@RequestBody AuthenticationEntity request) {
        AuthenticationResponse res = service.authenticateUser(request);
        return ResponseEntity.ok(service.authenticateUser(request));
    }
    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> registerUser(@RequestBody RegistrationEntity request) {

        return ResponseEntity.ok(service.createUser(request));
    }

    @PostMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) {
            new SecurityContextLogoutHandler().logout(request,response,authentication);
            return "logged out";
        }
        return "failed!!";
    }

    @PostMapping("/token/validate")
    public ResponseEntity<?> validateToken(@RequestBody TokenAuth request) {
        System.out.println(request);
        boolean result = service.validateToken(request);
        return ResponseEntity.ok(result);
    }
}
