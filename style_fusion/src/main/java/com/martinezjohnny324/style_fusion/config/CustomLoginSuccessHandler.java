package com.martinezjohnny324.style_fusion.config;


import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import com.martinezjohnny324.style_fusion.repositories.UserRepository;

@Component
public class CustomLoginSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

    private final UserRepository userRepository;

    public CustomLoginSuccessHandler(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void resetFailedAttempts(String username) {
        if (username != null) {
            userRepository.findByUsername(username).ifPresent(user -> {
                user.setFailedAttempts(0);
                user.setAcccountNonLocked(true);
                user.setLockTime(null);
                userRepository.save(user);
            });
        }
    }
}