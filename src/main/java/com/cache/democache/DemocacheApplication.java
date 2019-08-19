package com.cache.democache;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@MapperScan("com.cache.democache.mapper")
@SpringBootApplication
@EnableCaching//可缓存的
public class DemocacheApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemocacheApplication.class, args);
    }

}
