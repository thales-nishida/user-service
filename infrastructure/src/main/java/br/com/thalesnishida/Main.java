package br.com.thalesnishida.user.service.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Main {
    public static void main(String[] args) {
        Systen.out.println("Hello World");
        SpringApplication.run(args);
    }
}


