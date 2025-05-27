package br.com.thalesnishida.user.service.infrastructure;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import br.com.thalesnishida.user.service.infrastructure.configuration.WebServerConfig;

@SpringBootApplication
public class Main {

    public static void main(String[] args) {
        System.out.println("Hello World");
        SpringApplication.run(WebServerConfig.class, args);
    }
}


