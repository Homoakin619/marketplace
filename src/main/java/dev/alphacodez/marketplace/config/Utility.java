package dev.alphacodez.marketplace.config;

import com.cloudinary.Cloudinary;
import dev.alphacodez.marketplace.users.User;
import dev.alphacodez.marketplace.users.UserRepository;

import lombok.RequiredArgsConstructor;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class Utility {
    private final Cloudinary cloudinary;
    private final UserRepository userRepository;

    public String createImageUpload(MultipartFile image,String folderName) throws IOException {
        String url = null;
        if (!image.isEmpty()) {
            try {
                Map params = new HashMap<>();
                params.put("folder", folderName);
                url = cloudinary.uploader().upload(image.getBytes(),params).get("url").toString();
            } catch (IOException e) {

            }
        }
        return url;
    }


    public User getAuthenticatedUser() throws UsernameNotFoundException{
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            User user = (User) authentication.getPrincipal();
            return user;
        }
        throw new IllegalStateException("User is not found");

    }
}
