package org.example;

public class ClassicalMusic implements Music{
    private ClassicalMusic(){}

    public static ClassicalMusic getClassicalMusic(){
        return new ClassicalMusic();
    }
    public void doMyInit(){
        System.out.println("Initialized Classical music");
    }

    public void doMyDestroy(){
        System.out.println("Classical music was destroyed");
    }
    @Override
    public String getSong() {
        return "Hungarian Rhapsody";
    }
}
