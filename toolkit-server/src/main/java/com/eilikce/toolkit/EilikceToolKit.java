package com.eilikce.toolkit;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.eilikce.toolkit.kits.mysql.dao")
public class EilikceToolKit {

    public static void main(String[] args) {
        SpringApplication.run(EilikceToolKit.class, args);
    }

}
