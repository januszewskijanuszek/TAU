package com.example.demo.user;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class UserConfiguration {

    // Save to database
//    @Bean
//    CommandLineRunner commandLineRunner(UserRepository userRepository){
//        return args -> {
//            User test1 = new User("Marik", "1234", "marik@gmail.com");
//            User test2 = new User("marian", "4321", "marian@gmail.com");
//
//            userRepository.saveAll(List.of(test1, test2));
//        };
//    }
}
