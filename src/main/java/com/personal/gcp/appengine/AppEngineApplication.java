package com.personal.gcp.appengine;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = "com.personal.gcp.appengine")
@EnableJpaRepositories("com.personal.gcp.appengine.repository")
@EntityScan("com.personal.gcp.appengine.entities")
public class AppEngineApplication {

    public static void main(String[] args) {
        SpringApplication.run(AppEngineApplication.class, args);
    }

}
