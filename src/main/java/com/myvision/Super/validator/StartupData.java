package com.myvision.Super.validator;

import com.myvision.Super.Entity.User;
import com.myvision.Super.Services.UserService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class StartupData implements CommandLineRunner {
    private final UserService userService;
    private static final Logger logger = LoggerFactory.getLogger(StartupData.class);

    @Autowired
    public StartupData(UserService userService) {
        this.userService = userService;

    }

    @Override
    public void run(String... args) {
        /*adminAccount();*/
     /*   userAccount();*/

    }

    private void userAccount() {
        User user = new User();

        user.setUsername("user");
        user.setPassword("user");
        user.setGender("Female");
        user.setEmail("user@example.com");

        userService.save(user);
    }

    private void adminAccount() {
        User admin = new User();

        admin.setUsername("admin");
        admin.setPassword("admin");
        admin.setGender("Male");
        admin.setEmail("admin@example.com");

        userService.save(admin);
    }


}
