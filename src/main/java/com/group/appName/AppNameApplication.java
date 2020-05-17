package com.group.appName;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@SpringBootApplication
@EnableJpaRepositories
public class AppNameApplication {
    public static void main(String[] args) {
        SpringApplication.run(AppNameApplication.class, args);

    }
}


