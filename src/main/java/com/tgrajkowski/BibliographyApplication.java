package com.tgrajkowski;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.io.IOException;

@SpringBootApplication
public class BibliographyApplication {
    public static void main(String[] args) {
        SpringApplication.run(BibliographyApplication.class, args);
    }
}

