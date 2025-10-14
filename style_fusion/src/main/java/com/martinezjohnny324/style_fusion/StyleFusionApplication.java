package com.martinezjohnny324.style_fusion;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class StyleFusionApplication implements CommandLineRunner { // 1. Implementa CommandLineRunner

    // Definimos el Logger y el nombre de la DB (se inyectará automáticamente)
    private static final Logger log = LoggerFactory.getLogger(StyleFusionApplication.class);

    // 2. Inyectamos la URL de la base de datos desde application.properties
    @Value("${spring.datasource.url}")
    private String databaseUrl;

    public static void main(String[] args) {
        SpringApplication.run(StyleFusionApplication.class, args);
    }

    // 3. Este método se ejecuta DESPUÉS de que el contexto de Spring esté cargado (y la conexión establecida)
    @Override
    public void run(String... args) throws Exception {
        
        // Lógica para extraer el nombre de la base de datos de la URL
        String dbName = "UNKNOWN";
        try {
            int lastSlash = databaseUrl.lastIndexOf("/");
            int endQuery = databaseUrl.indexOf("?");
            
            if (lastSlash != -1 && endQuery != -1 && endQuery > lastSlash) {
                dbName = databaseUrl.substring(lastSlash + 1, endQuery);
            } else if (lastSlash != -1) {
                // Caso sin parámetros de query al final
                dbName = databaseUrl.substring(lastSlash + 1);
            }
        } catch (Exception e) {
            log.warn("No se pudo analizar la URL de la base de datos para obtener el nombre.");
        }
        
        // 4. Imprime el mensaje final de éxito
        log.info("------------------------------------------------------------------");
        log.info("✅ ¡Conexión exitosa! La aplicación está conectada a la DB: [" + dbName + "]");
        log.info("------------------------------------------------------------------");
    }
}
