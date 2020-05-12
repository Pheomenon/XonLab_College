package com.xonlab.aclservice;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

/**
 * @Author:Gao
 * @Date:2020-05-11 20:33
 */
@SpringBootApplication
@EnableDiscoveryClient
@ComponentScan("com.xonlab")
@MapperScan("com.xonlab.aclservice.mapper")
public class ServiceAclApplication {
    public static void main(String[] args) {
        SpringApplication.run(ServiceAclApplication.class,args);
    }
}
