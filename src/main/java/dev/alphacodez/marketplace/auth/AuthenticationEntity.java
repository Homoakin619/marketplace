package dev.alphacodez.marketplace.auth;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class AuthenticationEntity {
    private String email;
    private String password;
}
