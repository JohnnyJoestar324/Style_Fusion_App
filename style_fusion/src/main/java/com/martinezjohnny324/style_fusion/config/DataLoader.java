package com.martinezjohnny324.style_fusion.config;

import com.martinezjohnny324.style_fusion.models.Users;
import com.martinezjohnny324.style_fusion.repositories.UserRepository;
import com.martinezjohnny324.style_fusion.models.Membresia; 
import com.martinezjohnny324.style_fusion.repositories.MembresiaRepository; 
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.List; 

@Configuration
public class DataLoader {

    private static final Logger log = LoggerFactory.getLogger(DataLoader.class);

    @Bean
    public CommandLineRunner initDatabase(
        UserRepository usersRepository, 
        PasswordEncoder passwordEncoder,
        MembresiaRepository membresiaRepository) { // ⬅️ INYECTADO
        
        return args -> {
            
            // ==========================================================
            // LOGICA DE CARGA DE USUARIO ADMIN
            // ==========================================================
            if (usersRepository.findByUsername("admin").isEmpty()) {
                Users admin = new Users(
                    "Admin Principal", 
                    "admin", 
                    "admin@stylefusion.com", 
                    passwordEncoder.encode("admin123"),
                    "ROLE_ADMIN" 
                );
                usersRepository.save(admin);
                log.info("✅ Administrador inicial creado.");
                log.info("   Usuario: admin | Contraseña: admin123 | Rol: ADMIN");
            } else {
                log.info("ℹ️ El administrador ya existe. Omitiendo creación.");
            }

            // ==========================================================
            // LOGICA DE CARGA DE MEMBRESÍAS
            // ==========================================================
            if (membresiaRepository.count() == 0) {
                
                Membresia basica = new Membresia();
                basica.setNombrePlan("Básico");
                basica.setPrecio(10.00); 
                basica.setDescripcion("Acceso estándar.");
                
                Membresia profesional = new Membresia();
                profesional.setNombrePlan("Profesional");
                profesional.setPrecio(20.00);
                profesional.setDescripcion("Acceso premium.");

                Membresia premium = new Membresia();
                premium.setNombrePlan("Premium");
                premium.setPrecio(25.00);
                premium.setDescripcion("Acceso completo y soporte prioritario.");
                
                // Guardar los 3 planes en la base de datos
                membresiaRepository.saveAll(List.of(basica, profesional, premium));
                
                log.info("✅ Membresías predeterminadas cargadas: Básico ($10), Profesional ($20), Premium ($25).");
            } else {
                log.info("ℹ️ Membresías ya existentes. Omitiendo carga de planes.");
            }
        };
    }
}