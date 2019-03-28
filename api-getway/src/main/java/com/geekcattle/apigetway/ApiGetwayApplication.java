package com.geekcattle.apigetway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@EnableZuulProxy
@SpringBootApplication
public class ApiGetwayApplication {

    public static void main(String[] args) {
        SpringApplication.run(ApiGetwayApplication.class, args);
    }

}
