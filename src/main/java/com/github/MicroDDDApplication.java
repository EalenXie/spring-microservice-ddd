package com.github;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author EalenXie create on 2018/8/24 15:16.
 */
@SpringBootApplication
@MapperScan("com.github.domain.repository")
public class MicroDDDApplication {

    public static void main(String[] args) {
        SpringApplication.run(MicroDDDApplication.class, args);
    }

}
