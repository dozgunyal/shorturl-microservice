package com.dozgunyal.service.shorturl;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;

@SpringBootApplication(exclude = RedisAutoConfiguration.class)
public class Application {

    public static void main(String[] args) {
    	SpringApplication.run(Application.class, args);
    }

}
