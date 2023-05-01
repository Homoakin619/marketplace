package dev.alphacodez.marketplace.auth;

import lombok.Data;

@Data
public class PasswordChange {

    private String oldPassword;
    private String newPassword;
}
