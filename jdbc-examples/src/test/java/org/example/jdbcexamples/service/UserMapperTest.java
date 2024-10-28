package org.example.jdbcexamples.service;

import lombok.extern.slf4j.Slf4j;
import org.example.jdbcexamples.dox.Address;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
class UserMapperTest {

    @Autowired
    UserMapper userMapper;
    @Test
    void findUserByAddressId() {
        log.debug("{}", userMapper.findUserByAddressId("3"));
    }


    @Test
    void findUserByAddressId2() {
        log.debug("{}", userMapper.findUserByAddressId2("3"));
    }

    @Test
    void findAddressByUid() {
        for(Address address: userMapper.findAddressByUid("1284873941642883072").getAddresses()){
            log.debug("{}",address);
        }
    }
}