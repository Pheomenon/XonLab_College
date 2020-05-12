package com.xonlab.staservice;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @Author:Gao
 * @Date:2020-05-06 15:20
 */
@SpringBootApplication
@ComponentScan(basePackages = {"com.xonlab"})
@EnableDiscoveryClient
@EnableFeignClients
@MapperScan("com.xonlab.staservice.mapper")
@EnableScheduling
public class StaApplication {
    public static void main(String[] args) {
        SpringApplication.run(StaApplication.class,args);
    }
}
