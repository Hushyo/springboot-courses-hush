package org.example.springmvcexample.service;


import org.example.springmvcexample.dox.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private static List<User> USERS = create();
    //模拟一个数据库，目前只有一条记录
    private static List<User> create(){
        User u = User.builder()
                .id("1")
                .name("Hush")
                .account("Hushyo")
                .password("123456")
                .build();
        return List.of(u);
    }
    //对外暴露获取User方法
    public List<User> listUsers(){
        return USERS;
    }
    public User getUserByAccount(String account, String password){
        return USERS.stream()
                .filter(u->u.getAccount().equals(account))
                .filter(u->u.getPassword().equals(password))
                .findFirst()
                .orElse(null);
    }
}
