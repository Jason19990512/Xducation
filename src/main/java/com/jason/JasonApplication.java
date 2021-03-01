package com.jason;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication
public class JasonApplication {

    public static void main(String[] args) {
        SpringApplication.run(JasonApplication.class, args);
    }

}
