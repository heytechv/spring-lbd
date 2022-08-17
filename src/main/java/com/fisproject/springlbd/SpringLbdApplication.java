package com.fisproject.springlbd;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import javax.annotation.PostConstruct;
import javax.persistence.PrePersist;

@SpringBootApplication
public class SpringLbdApplication {


    public static void main(String[] args) {
        SpringApplication.run(SpringLbdApplication.class, args);

        System.out.println("SIEMA2");

    }

}
