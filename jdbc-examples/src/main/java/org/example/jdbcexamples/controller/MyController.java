package org.example.jdbcexamples.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.jdbcexamples.dox.User;
import org.example.jdbcexamples.repository.UserRepository;
import org.example.jdbcexamples.repository.UserRepositoryMock;
import org.example.jdbcexamples.service.UserService;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Slf4j
@RestController
@RequestMapping("/api/")
@RequiredArgsConstructor
public class MyController {
    private final UserRepository userRepository;
    private final UserService userService;
    private final UserRepositoryMock userRepositoryMock;
    //这个为什么不好使？
    /*

    @Cacheable(value="user",key="abcd")
    @GetMapping("users")
    public List<User> getUsers() {
        User.count++;
        System.out.println(User.count+"次使用方法");
        return userRepositoryMock.getUsers();
    }

     */

    @Cacheable(value="users",key="#id")
    @GetMapping("users/{id}")
    public User getUser(@PathVariable("id") String id) {
        User user = userRepository.findUserById(id);
        System.out.println("调用查询方法");
        return user;
    }

    @PostMapping("users/update")
    public User updateUser(@RequestBody User user) {
        return userService.updateUserById(user);
    }

    @CacheEvict(value="users",key="#id")
    @GetMapping("users/delete/{id}")
    public void deleteUser(@PathVariable("id") String id) {
        //删除缓存而已，什么都不需要调用
    }

}
