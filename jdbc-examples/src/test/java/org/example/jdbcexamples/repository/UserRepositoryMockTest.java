package org.example.jdbcexamples.repository;

import lombok.extern.slf4j.Slf4j;
import org.example.jdbcexamples.dox.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Service;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@Slf4j
class UserRepositoryMockTest {

    @Autowired
    private UserRepositoryMock userRepositoryMock;

}