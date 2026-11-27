package com.martinezjohnny324.style_fusion.config;

import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import java.io.IOException;
import java.time.LocalDateTime;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;
import com.martinezjohnny324.style_fusion.repositories.UserRepository;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class CustomLoginFailureHandler extends SimpleUrlAuthenticationFailureHandler {

    private static final int MAX_FAILED_ATTEMPTS = 5;
    private final UserRepository userRepository;

    public CustomLoginFailureHandler(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void onAuthenticationFailure(
            HttpServletRequest request,
            HttpServletResponse response,
            AuthenticationException exception) throws IOException, ServletException {

        String username = request.getParameter("username");
        String redirectUrl = "/login?error";

        if (exception instanceof LockedException) {
            redirectUrl = "/login?locked";
        } else if (exception instanceof BadCredentialsException && username != null) {
            userRepository.findByUsername(username).ifPresent(user -> {
                if (user.isAcccountNonLocked()) {
                    int attempts = user.getFailedAttempts() + 1;
                    if (attempts >= MAX_FAILED_ATTEMPTS) {
                        user.setFailedAttempts(attempts);
                        user.setAcccountNonLocked(false);
                        user.setLockTime(LocalDateTime.now());
                    } else {
                        user.setFailedAttempts(attempts);
                    }
                    userRepository.save(user);
                }
            });
        }

        super.setDefaultFailureUrl(redirectUrl);
        super.onAuthenticationFailure(request, response, exception);
    }
}
    

