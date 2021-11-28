package com.example.my1119;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class My1119Application {

    public static void main(String[] args) {
        SpringApplication.run(My1119Application.class, args);
    }

}
