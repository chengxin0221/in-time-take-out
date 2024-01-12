package com.intime;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//@EnableTransactionManagement    //开启注解方式的事务管理
@Slf4j
public class InTimeApplication {
    public static void main(String[] args){
        SpringApplication.run(InTimeApplication.class, args);
        log.info("server started");
    }
}
