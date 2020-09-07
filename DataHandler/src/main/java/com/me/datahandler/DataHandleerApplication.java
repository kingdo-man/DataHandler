package com.me.datahandler;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages="com.me.datahandler.dao.mapper")
public class DataHandleerApplication {

    public static void main(String[] args) {
        SpringApplication.run(DataHandleerApplication.class, args);
    }

}
