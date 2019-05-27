package com.tgrajkowski;

import brave.sampler.Sampler;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.context.annotation.Bean;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients("com.tgrajkowski")
@EnableDiscoveryClient
@EnableHystrix
@SpringBootApplication
public class BibliographyApplication {
    @Value("${info.company.name}")
    private static String myString;

    public static void main(String[] args) {
        System.out.println("checkoing is it really working...: "+myString);
        SpringApplication.run(BibliographyApplication.class, args);
    }
    @Bean
    public Sampler defaultSampler() {
        return Sampler.ALWAYS_SAMPLE;
    }
}

