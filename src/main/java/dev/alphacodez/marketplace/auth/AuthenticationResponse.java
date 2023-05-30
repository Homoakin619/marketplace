package dev.alphacodez.marketplace.auth;

import dev.alphacodez.marketplace.users.Role;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@Builder
@ToString
public class AuthenticationResponse {
    private String token;
    private boolean success = false;
    private String email = "";
    private Role role;
}
