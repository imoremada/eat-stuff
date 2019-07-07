package com.progex.eats;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan("com.progex.eats")
@Slf4j
public class App {
    public static void main(String[] args) {
        log.info("starting info");
        log.error("starting error");
        SpringApplication.run(App.class, args);
    }
}
