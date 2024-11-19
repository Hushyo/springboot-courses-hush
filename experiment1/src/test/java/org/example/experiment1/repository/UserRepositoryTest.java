package org.example.experiment1.repository;

import lombok.extern.slf4j.Slf4j;
import org.example.experiment1.dox.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    void save(){
        userRepository.save(User.builder().name("testName").build());
    }

    @Test
    void findAll() {
        userRepository.findAll().forEach(u->{log.debug("{}",u);});
    }

    @Test
    void findByUserId() {
        log.debug("{}",userRepository.findByUserId("1308436864775729152"));
    }

    @Test
    void updateUserNameById() {
        userRepository.updateUserNameById("newName","1308436864775729152");
        userRepository.findByUserId("1308436864775729152");
    }

    @Test
    void deleteUserById() {
        userRepository.deleteUserById("1308436864775729152");
        userRepository.findAll().forEach(u->{log.debug("{}",u);});
    }
}