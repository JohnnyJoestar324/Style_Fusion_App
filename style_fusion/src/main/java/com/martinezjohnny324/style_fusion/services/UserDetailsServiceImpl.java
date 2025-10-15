package com.martinezjohnny324.style_fusion.services;

import com.martinezjohnny324.style_fusion.models.Users;
import com.martinezjohnny324.style_fusion.repositories.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.authority.SimpleGrantedAuthority; // Necesario para los roles
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service // Esto lo registra como un bean de Spring
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository usersRepository;

    public UserDetailsServiceImpl(UserRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    // Método que Spring Security llama cuando alguien intenta iniciar sesión
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        
        // 1. Buscar al usuario en la base de datos
        Users user = usersRepository.findByUsername(username)
            .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado: " + username));
        
        // 2. Convertir tu objeto Users a un objeto UserDetails que Spring Security entiende
        return new org.springframework.security.core.userdetails.User(
            user.getUsername(),
            user.getPassword(), // ¡La contraseña encriptada!
            Collections.singletonList(new SimpleGrantedAuthority(user.getRole()))
        );
    }
}