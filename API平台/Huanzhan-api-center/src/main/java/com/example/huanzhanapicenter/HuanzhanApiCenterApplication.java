package com.example.huanzhanapicenter;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.example.huanzhanapicenter.mapper")

public class HuanzhanApiCenterApplication {

    public static void main(String[] args) {
        SpringApplication.run(HuanzhanApiCenterApplication.class, args);
    }

}
