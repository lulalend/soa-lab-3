package ru.itmo.soa.grammyservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class GrammyServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(GrammyServiceApplication.class, args);
    }

}
