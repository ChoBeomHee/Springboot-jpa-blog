package com.cho.springblog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


//scanBasePackages = {"com.cho.blog.repository"}
@SpringBootApplication()
@EntityScan(basePackages = {"com.cho.springblog.model"})
@EnableJpaRepositories(basePackages = {"com.cho.springblog.repository"})

public class SpringblogApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpringblogApplication.class, args);
    }
}
