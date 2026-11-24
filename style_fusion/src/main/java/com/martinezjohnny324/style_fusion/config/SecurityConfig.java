package com.martinezjohnny324.style_fusion.config;

import org.springframework.context.annotation.Bean;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import java.util.Collection;

@Configuration
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/images/**","/auth","/auth/registro", "/login/**", "/css/**", "/js/**").permitAll() // acceso libre
                .requestMatchers("/admin/**").hasRole("ADMIN")
                .requestMatchers("/home/").hasRole("ADMIN")
                .requestMatchers("/membresia/**").hasRole("ADMIN")
                .requestMatchers("/cliente/**").hasAnyRole("ADMIN")
                .requestMatchers("/home_negocio").hasRole("CLIENTE")
                .requestMatchers("/barbero/**").hasAnyRole("CLIENTE")
                .requestMatchers("/cliente-barbero/**").hasAnyRole("CLIENTE")
                .anyRequest().authenticated()
                
            )

            .exceptionHandling(ex -> ex
                .accessDeniedPage("/Acceso_Denegado")
             )
        


            .formLogin(form -> form
                .loginPage("/login")
                .loginProcessingUrl("/login")
                .successHandler((HttpServletRequest request, HttpServletResponse response, Authentication authentication) -> {
                    Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
                    String redirectURL = request.getContextPath();

                    for (GrantedAuthority auth : authorities) {
                        String role = auth.getAuthority();
                        if (role.equals("ROLE_ADMIN")) {
                            redirectURL = "/home";
                            break;
                        } else if (role.equals("ROLE_CLIENTE")) {
                            redirectURL = "/home_negocio";
                            break;
                        
                        }
                    }

                    response.sendRedirect(redirectURL);
                })
                .permitAll()
            )
            .logout(logout -> logout
                .logoutUrl("/logout")
                .logoutSuccessUrl("/login?logout")
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID")
                .permitAll()
            );

        return http.build();
    }
}
