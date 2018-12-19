package com.github.leofalco;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

@SpringBootApplication
public class CursomcApplication implements CommandLineRunner {

    private final MockTestDataService mockTestDataService;

    @Autowired
    public CursomcApplication(MockTestDataService mockTestDataService) {
        this.mockTestDataService = mockTestDataService;
    }

    public static void main(String[] args) {
        SpringApplication.run(CursomcApplication.class, args);
    }

    @Override
    public void run(String... args) {

        Properties properties = new Properties();

        InputStream resourceAsStream = CursomcApplication.class.getResourceAsStream("/application.properties");

        try {
            properties.load(resourceAsStream);
        } catch (IOException e) {
            e.printStackTrace();
        }

        String mockData = properties.getProperty("mock-data");

        if (mockData.equals("true")) {
            mockTestDataService.mock();

        }

    }
}
