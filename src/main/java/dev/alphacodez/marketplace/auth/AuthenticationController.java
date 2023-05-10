package dev.alphacodez.marketplace.auth;


import dev.alphacodez.marketplace.config.security.JwtService;
import dev.alphacodez.marketplace.exceptions.UserExistsException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;


@RequiredArgsConstructor
@RequestMapping("api/v1/auth")
@RestController
public class AuthenticationController {
    private final AuthenticationService service;
    private final JwtService jwtService;
    private final HttpServletRequest httpServletRequest;

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> loginUser(@RequestBody AuthenticationEntity request) {

        return ResponseEntity.ok(service.authenticateUser(request));
    }
    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody RegistrationEntity request) {
        try {
            AuthenticationResponse response = service.createUser(request);
            return ResponseEntity.ok(response);
        } catch (UserExistsException ex) {
           return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

    @PostMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) {
            httpServletRequest.logout();
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
