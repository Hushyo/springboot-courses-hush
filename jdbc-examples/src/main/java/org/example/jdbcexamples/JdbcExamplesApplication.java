package org.example.jdbcexamples;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
public class JdbcExamplesApplication {

    public static void main(String[] args) {
        SpringApplication.run(JdbcExamplesApplication.class, args);
    }

}
