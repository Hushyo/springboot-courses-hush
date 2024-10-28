package org.example.jdbcexamples.service;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.jdbcexamples.dox.User;
import org.example.jdbcexamples.repository.UserRepository;
import org.springframework.cache.annotation.CachePut;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {

    private final UserRepository userRepository;

    @CachePut(value="users",key="#user.id")
    public User updateUserById(User user){

        System.out.println("调用更新方法");
        userRepository.updateUserById(user.getId(),user.getName());

        return userRepository.findUserById(user.getId());
    }

}
