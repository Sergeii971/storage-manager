package com.os.course;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

@SpringBootApplication
@ComponentScan(basePackages = {"com.os.course"})
@EnableJpaRepositories("com.os.course.persistent.repository")
@EntityScan("com.os.course.model.entity")
@EnableResourceServer
public class Main {
    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }
}