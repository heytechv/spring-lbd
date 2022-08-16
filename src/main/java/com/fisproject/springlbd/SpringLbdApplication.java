package com.fisproject.springlbd;

import com.fisproject.springlbd.utils.CreateRandomSprints;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;

@SpringBootApplication
public class SpringLbdApplication {

    @Autowired CreateRandomSprints createRandomSprints;

    @PostConstruct
    void init() { }

    public static void main(String[] args) {
        SpringApplication.run(SpringLbdApplication.class, args);
        System.out.println("SIEMA2");

    }

}
