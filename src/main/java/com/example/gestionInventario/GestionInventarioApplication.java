package com.example.gestionInventario;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"controllers", "dao"})
@EntityScan("models")
public class GestionInventarioApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(GestionInventarioApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("MYSQL_DATABASE: " + System.getenv("MYSQL_DATABASE"));
        System.out.println("RAILWAY_TCP_PROXY_DOMAIN: " + System.getenv("RAILWAY_TCP_PROXY_DOMAIN"));
        System.out.println("MYSQL_ROOT_PASSWORD: " + System.getenv("MYSQL_ROOT_PASSWORD"));
        System.out.println("RAILWAY_TCP_PROXY_PORT: " + System.getenv("RAILWAY_TCP_PROXY_PORT"));
    }

}
