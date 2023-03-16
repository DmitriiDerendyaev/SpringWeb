package org.example;

import org.springframework.context.Lifecycle;
import org.springframework.context.annotation.*;
import org.springframework.stereotype.Component;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

@Configuration
@ComponentScan("org.example")
@PropertySource("classpath:musicPlayer.properties")
public class SpringConfig {
    @Bean
    public ClassicalMusic classicalMusic(){
        return new ClassicalMusic();
    }

    @Bean
    public RockMusic rockMusic(){
        return new RockMusic();
    }

    @Bean
    public JazzMusic jazzMusic(){
        return new JazzMusic();
    }

    @Bean
    public List<Music> genreList(){
        return Arrays.asList(classicalMusic(), rockMusic(), jazzMusic());
    }
    @Bean
    public MusicPlayer musicPlayer(){
        return new MusicPlayer(genreList());
    }

    @Bean Computer computer(){
        return new Computer(musicPlayer());
    }
}
