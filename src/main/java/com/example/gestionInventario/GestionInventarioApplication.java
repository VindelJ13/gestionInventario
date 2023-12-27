package com.example.gestionInventario;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"controllers", "dao"})
@EntityScan("models")
public class GestionInventarioApplication {

    public static void main(String[] args) {
        SpringApplication.run(GestionInventarioApplication.class, args);
    }

}
