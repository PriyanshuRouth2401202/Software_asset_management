package com.cognizant.userservice;

import java.lang.reflect.InvocationTargetException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.cognizant.userservice.model.User;
import com.cognizant.userservice.service.DatabaseService;


@SpringBootApplication
public class UserserviceApplication {

	@Autowired
    private DatabaseService databaseService;

    public static void main(String[] args) {
        SpringApplication.run(UserserviceApplication.class, args);
    }

    
    public void run(String... args) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
        databaseService.ensureTableExists(User.class);  // Checks & creates the 'User' table
    }
    }

