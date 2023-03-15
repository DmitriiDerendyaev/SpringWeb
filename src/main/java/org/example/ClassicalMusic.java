package org.example;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class ClassicalMusic implements Music{
    List<String> listClassical = new ArrayList<>(Arrays.asList("Swan Lake: Spanish Dance", "Palladio", "The four seasons"));

    private ClassicalMusic(){}

    @Override
    public List<String> getSong() {
        return listClassical;
    }
}
