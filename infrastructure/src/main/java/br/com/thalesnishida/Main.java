package br.com.thalesnishida.user.service.infrastructure;

import br.com.thalesnishida.user.service.infrastructure.configuration.WebServerConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.AbstractEnvironment;

@SpringBootApplication
public class Main {

  public static void main(String[] args) {
    System.out.println("Hello World!");
    // System.setProperty(AbstractEnvironment.ACTIVE_PROFILES_PROPERTY_NAME, "development");
    System.setProperty(AbstractEnvironment.DEFAULT_PROFILES_PROPERTY_NAME, "development");
    SpringApplication.run(WebServerConfig.class, args);
  }
}
