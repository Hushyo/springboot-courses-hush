package org.example.jdbcexamples.service;

import lombok.extern.slf4j.Slf4j;
import org.example.jdbcexamples.dox.Address;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@Slf4j
class UserServiceTest {

    @Autowired
    UserService userService;
    @Test
    void findUserByAddressId() {
        log.debug("{}",userService.findUserByAddressId("3"));
    }


    @Test
    void findUserByAddressId2() {
        log.debug("{}",userService.findUserByAddressId2("3"));
    }

    @Test
    void findAddressByUid() {
        for(Address address: userService.findAddressByUid("1284873941642883072").getAddresses()){
            log.debug("{}",address);
        }
    }
}