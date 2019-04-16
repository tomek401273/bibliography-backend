package com.tgrajkowski;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

//import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
@EnableDiscoveryClient
//@EnableResourceServer
@SpringBootApplication
public class BibliographyApplication {
    public static void main(String[] args) {
        SpringApplication.run(BibliographyApplication.class, args);
    }
}

