package com.whu.charityangel;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.whu.charityangel.mapper")
@SpringBootApplication
public class CharityAngelApplication {

    public static void main(String[] args) {
        SpringApplication.run(CharityAngelApplication.class, args);
    }

}
