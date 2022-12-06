package com.zhaostudy.system;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Created with IntelliJ IDEA.
 *
 * @User：mac
 * @Author: yykk
 * @Date: 2022/10/31/10:43 AM
 * @Description: https://www.cnblogs.com/zhaostudy/
 */

@SpringBootApplication
@MapperScan("com.zhaostudy.system.mapper")
public class ServiceAuthApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServiceAuthApplication.class,args);
    }
}
