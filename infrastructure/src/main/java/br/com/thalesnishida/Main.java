package br.com.thalesnishida.user.service.infrastructure;

import org.springframework.boot.SpringApplication;
import org.springframework.core.env.AbstractEnvironment;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import br.com.thalesnishida.user.service.infrastructure.configuration.WebServerConfig;

@SpringBootApplication
public class Main {

    public static void main(String[] args) {
        System.out.println("Hello World!");
        // System.setProperty(AbstractEnvironment.ACTIVE_PROFILES_PROPERTY_NAME, "development");
        System.setProperty(AbstractEnvironment.DEFAULT_PROFILES_PROPERTY_NAME, "development");
        SpringApplication.run(WebServerConfig.class, args);
    }
}


