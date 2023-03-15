package org.example;

import java.util.Collections;
import java.util.List;

//@Component
public class PopMusic implements Music{
    private PopMusic(){}

    public static PopMusic getPopMusic(){
        return new PopMusic();
    }
    public void initClassic(){
        System.out.println("Initialized Pop music");
    }

    public void destroyClassic(){
        System.out.println("Pop music was destroyed");
    }



    @Override
    public List<String> getSong() {
        return Collections.singletonList("Take on Me");
    }
}
