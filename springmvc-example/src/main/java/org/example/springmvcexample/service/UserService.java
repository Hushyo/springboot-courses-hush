package org.example.springmvcexample.service;


import org.example.springmvcexample.dox.User;
import org.example.springmvcexample.dox.User01;
import org.example.springmvcexample.excepiton.myException;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

@Service
public class UserService {
    private static List<User> USERS = create();
    //模拟一个数据库，目前只有一条记录
    private static List<User> create(){
        User u = User.builder()
                .id("1")
                .name("Hush")
                .role(User.ADMIN)
                .account("1001")
                .password("$2a$10$fsvEybsOsfW6p7MRJFtBEu5bTmRA/tAMiq2AF7wJt/QViokmtJKra")
                .build();
        User u2 = User.builder()
                .id("2")
                .name("user2")
                .role(User.USER)
                .account("1002")
                .password("$2a$10$fsvEybsOsfW6p7MRJFtBEu5bTmRA/tAMiq2AF7wJt/QViokmtJKra")
                .build();
        return List.of(u,u2);
    }
    public List<User> listUsers(){
        return USERS;
    }
/*
    public User getUserByAccount(String account, String password){
        return USERS.stream()
                .filter(u->u.getAccount().equals(account))
                .filter(u->u.getPassword().equals(password))
                .findFirst()
                .orElse(null);
    }
    */

    public User getUserByAccount(String account){
        return USERS.stream()//从用户列表里过滤匹配 account的用户
                .filter(u->u.getAccount().equals(account))
                .findFirst()
                .orElse(null);
    }

    public void readFile(){
        try{
            Files.readString(Path.of("A:/aa.txt"));
        }catch(IOException e){
            throw new myException().builder()
                    .codeN(500)
                    .message(" 读取文件失败"+e.getMessage())
                    .build();
        }
    }
    //模拟一个已经存在的用户
    public User01 getUser01(String userName){
        return "pang".equals(userName)?
                User01.builder()
                        .id("1")
                        .userName("pang")
                        .role("admin")
                        .password("$2a$10$8stYnVbowAnLjJUB94ydX.H9ybMgbbseeVO71xGUfqV74cnRYr8S6")
                        .build() : null;
    }

}
