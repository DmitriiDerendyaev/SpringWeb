package org.example;

import org.springframework.stereotype.Component;

@Component
public class ClassicalMusic implements Music{
    private ClassicalMusic(){}

    @Override
    public String getSong() {
        return "Hungarian Rhapsody";
    }
}
