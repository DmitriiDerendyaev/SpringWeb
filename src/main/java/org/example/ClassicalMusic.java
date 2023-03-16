package org.example;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

//@Component
//@Scope("prototype")
public class ClassicalMusic implements Music{
    @PostConstruct
    public void doMyInit(){
        System.out.println("Classical music was initialization");
    }

    @PreDestroy
    public void doMyDestroy(){
        System.out.println("Classical music was destroyed");
    }

    @Override
    public String getSong() {
        return "Hungarian Rhapsody";
    }
}
