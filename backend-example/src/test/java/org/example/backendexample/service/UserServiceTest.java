package org.example.backendexample.service;

import jogamp.common.Debug;
import lombok.extern.slf4j.Slf4j;
import org.example.backendexample.dox.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@Slf4j
class UserServiceTest {
    @Autowired
    private UserService userService;

    @Test
    void listUsers() {
        for(User u:userService.listUsers()){
            log.debug("{}",u);
        }
    }

    @Test
    void getUser() {
        log.debug("{}",userService.getUser("admin"));
    }

    @Test
    void getUserById() {
        log.debug("{}",userService.getUserById("1305551836551819264"));
    }

    @Test
    void updateUserPassword() {
        userService.updateUserPassword("admin");
    }

    @Test
    void updateUserPasswordById() {
        userService.updateUserPasswordById("1305551836551819264","admin");
    }

    @Test
    void addUser() {
        userService.addUser(User.builder()
                .name("persona")
                .role(User.USER)
                .password("persona")
                .account("persona")
                .build());
    }
}