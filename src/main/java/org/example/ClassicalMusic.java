package org.example;

public class ClassicalMusic implements Music{
    private ClassicalMusic(){}

    public static ClassicalMusic getClassicalMusic(){
        return new ClassicalMusic();
    }
    public void doMyInit(){
        System.out.println("Do my initialization");
    }

    public void doMyDestroy(){
        System.out.println("Bean was destroyed");
    }
    @Override
    public String getSong() {
        return "Hungarian Rhapsody";
    }
}
