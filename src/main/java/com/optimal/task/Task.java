package com.optimal.task;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.TimeZone;

@RequiredArgsConstructor
@SpringBootApplication
public class Task implements CommandLineRunner {


    public static void main(String[] args) {

        TimeZone.setDefault(TimeZone.getTimeZone("GMT+5:00"));
        SpringApplication.run(Task.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
    }
}