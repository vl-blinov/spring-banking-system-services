package ru.blinov.record;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@RefreshScope
@EnableEurekaClient
@EnableFeignClients
@SpringBootApplication
public class RecordApplication {
    public static void main(String[] args) {
        SpringApplication.run(RecordApplication.class, args);
    }
}
