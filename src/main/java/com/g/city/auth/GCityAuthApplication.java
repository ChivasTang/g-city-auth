package com.g.city.auth;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.g.city.auth"})
@MapperScan({"com.g.city.auth.mapper"})
public class GCityAuthApplication {

    public static void main(String[] args) {
        SpringApplication.run(GCityAuthApplication.class, args);
    }

}
