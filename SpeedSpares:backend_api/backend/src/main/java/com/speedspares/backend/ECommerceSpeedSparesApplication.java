package com.speedspares.backend; 
// Conflicto de paquetes resuelto, usando 'backend'

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// 1. Anotación clave para que Spring Boot funcione
@SpringBootApplication 
public class ECommerceSpeedSparesApplication {

    // 2. Método principal (MAIN)
    public static void main(String[] args) { 
        // 3. Comando para iniciar la aplicación
        SpringApplication.run(ECommerceSpeedSparesApplication.class, args); 
    }
}