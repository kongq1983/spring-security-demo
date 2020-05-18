package com.kq.first;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
public class SpringSecurityFirstApplication {

    @GetMapping("/")
    public String hello(){
        return "Welcome to Spring Security !";
    }


    public static void main(String[] args) {
        SpringApplication.run(SpringSecurityFirstApplication.class,args);
    }

}
