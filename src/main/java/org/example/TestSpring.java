package org.example;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestSpring {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
                "applicationContext.xml"
        );

        ClassicalMusic classicalMusic = context.getBean("musicBean", ClassicalMusic.class);
        ClassicalMusic classicalMusic1 = context.getBean("musicBean", ClassicalMusic.class);
        System.out.println(classicalMusic.getSong());
        System.out.println(classicalMusic1.getSong());

        System.out.println("\n\n");

        PopMusic popMusic = context.getBean("popBean", PopMusic.class);
        PopMusic popMusic1 = context.getBean("popBean", PopMusic.class);
        System.out.println(popMusic.getSong());
        System.out.println(popMusic1.getSong());


        context.close();

    }
}
