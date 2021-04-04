package ru.otus.restbooklibrary;

import com.github.cloudyrock.spring.v5.EnableMongock;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
@EnableWebMvc
@EnableMongock
public class Main {
    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }
}
