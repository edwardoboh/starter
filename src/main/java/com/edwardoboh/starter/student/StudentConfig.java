package com.edwardoboh.starter.student;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@Configuration
public class StudentConfig {

    @Bean
    public CommandLineRunner commandLineRunner(StudentRepository repository) {
        return args -> {
                Student alex = new Student("alex", "alex@gmail.com", LocalDate.of(2000, Month.APRIL, 12));
                Student nerd = new Student("nerd", "nerd@email.com", LocalDate.of(2008, Month.JUNE, 2));
                repository.saveAll(List.of(alex, nerd));
        };
    }

}
