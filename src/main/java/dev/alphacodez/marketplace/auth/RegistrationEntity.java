package dev.alphacodez.marketplace.auth;

import jakarta.persistence.Column;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class RegistrationEntity {
    private String name;
    private String email;
    private String password;
    private Long phone;
}
