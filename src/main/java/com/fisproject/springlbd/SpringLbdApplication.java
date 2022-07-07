package com.fisproject.springlbd;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringLbdApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringLbdApplication.class, args);

        String s="";

        if (s == null || s.isEmpty()) System.out.println("SIEMA");
        System.out.println("SIEMA2");

    }

}
