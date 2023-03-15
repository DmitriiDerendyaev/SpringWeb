package org.example;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component()
public class RockMusic implements Music{
    List<String> listRock = new ArrayList<>(Arrays.asList("Compromise", "No one writes to the colonel", "Smells like teen spirit"));
    @Override
    public List<String> getSong() {
        return listRock;
    }
}
