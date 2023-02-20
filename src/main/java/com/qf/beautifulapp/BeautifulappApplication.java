package com.qf.beautifulapp;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.qf.beautifulapp.dao")
public class BeautifulappApplication {

    public static void main(String[] args) {
        SpringApplication.run(BeautifulappApplication.class, args);
    }

}
