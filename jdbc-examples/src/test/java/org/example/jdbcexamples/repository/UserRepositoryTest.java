package org.example.jdbcexamples.repository;

import lombok.extern.slf4j.Slf4j;
import org.example.jdbcexamples.dox.Address;
import org.example.jdbcexamples.dox.User;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;


import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Slf4j
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    void save(){
        User user = User.builder().name("delete").build();
        userRepository.save(user);
    }

    @Test
    void findAll(){
        for(User user : userRepository.findAll()){
            log.debug("{}",user);
        }
    }

    @Test
    void findByPage(){
        int pageNumber = 1;
        int pageSize = 2;
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        for (User user : userRepository.findByPage(pageable)){
            log.debug("{}",user);
        }
    }

    @Test
    void updateUserById() {
        userRepository.updateUser("newName","1284886181666336768");
    }

    @Test
    void deleteUserById() {
        userRepository.deleteUserById("1296690196175630336");
    }

    @Test
    void findByUserId() {
        for (Address address : userRepository.findByUserId("1284886181666336768")) {
            System.out.println(address.toString());
        }
    }
}
