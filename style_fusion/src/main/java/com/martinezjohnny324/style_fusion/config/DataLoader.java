package com.martinezjohnny324.style_fusion.config;

import com.martinezjohnny324.style_fusion.models.Users;
import com.martinezjohnny324.style_fusion.repositories.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@Configuration
public class DataLoader {

    private static final Logger log = LoggerFactory.getLogger(DataLoader.class);

    @Bean
    public CommandLineRunner initDatabase(
        UserRepository usersRepository, 
        PasswordEncoder passwordEncoder) {
        
        return args -> {
            // 1. Intentar encontrar el administrador
            if (usersRepository.findByUsername("admin").isEmpty()) {
                
                // 2. Crear el usuario
                Users admin = new Users(
                    "Admin Principal", 
                    "admin", 
                    "admin@stylefusion.com", 
                    passwordEncoder.encode("admin123"), // ¡Encriptamos la contraseña!
                    "ROLE_ADMIN" // El rol debe coincidir con el usado en SecurityConfig: .hasRole("ADMIN")
                );
                
                // 3. Guardar en la BD
                usersRepository.save(admin);
                
                log.info("✅ Administrador inicial creado.");
                log.info("   Usuario: admin | Contraseña: admin123 | Rol: ADMIN");
            } else {
                log.info("ℹ️ El administrador ya existe. Omitiendo creación.");
            }
        };
    }
}