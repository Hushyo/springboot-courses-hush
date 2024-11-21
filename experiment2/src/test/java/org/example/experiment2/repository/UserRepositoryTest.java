package org.example.experiment2.repository;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Slf4j
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    void findUserAddressById() {
        System.out.println(userRepository.findUserAddressById("1308443180604829696").getUser());
        System.out.println(userRepository.findUserAddressById("1308443180604829696").getAddress());

    }

    @Test
    void findUserAddressListById() {
        System.out.println(userRepository.findUserAddressListById("1308443180604829696"));
    }
}