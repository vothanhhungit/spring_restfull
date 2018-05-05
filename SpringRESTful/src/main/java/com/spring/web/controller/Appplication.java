package com.spring.web.controller;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages={
    "com.spring.web"})
public class Appplication {

    public static void main(String[] args) {
        SpringApplication.run(Appplication.class, args);
    }

}

