package dev.alphacodez.marketplace.users;

import dev.alphacodez.marketplace.auth.PasswordChange;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;


@RequestMapping("api/v1/user")
@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService service;

    @PostMapping("/change")
    public String updatePassword(Principal currentUser, @RequestBody PasswordChange password, HttpServletRequest request) {
        String username = currentUser.getName();
        service.updateUser(username,password.getOldPassword(),password.getNewPassword());
//        request.getSession().invalidate();
//        SecurityContextHolder.clearContext();
        return "successful operation";
    }
}
