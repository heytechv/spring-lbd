package com.fisproject.springlbd;

import com.fisproject.springlbd.utils.CreateRandomSprints;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import javax.annotation.PostConstruct;
import javax.persistence.PrePersist;

@SpringBootApplication
public class SpringLbdApplication {

    @Autowired ApplicationContext context;

    @PostConstruct void init() {
        new CreateRandomSprints().create(context,5);
    }

    public static void main(String[] args) {
        SpringApplication.run(SpringLbdApplication.class, args);

        System.out.println("SIEMA2");

    }

}
