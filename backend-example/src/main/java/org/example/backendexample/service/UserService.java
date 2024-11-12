package org.example.backendexample.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.backendexample.dox.User;
import org.example.backendexample.excepiton.Code;
import org.example.backendexample.excepiton.myException;
import org.example.backendexample.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public List<User> listUsers(){
        return userRepository.findAll();
    }

    public User getUser(String account){
        return userRepository.findByAccount(account);
    }

    public User getUserById(String id){
        return userRepository.findById(id).orElse(null);
    }

    @Transactional
    public void updateUserPassword(String account){//用户改密码
        User user = getUser(account);
        if(user == null){
            throw myException.builder()
                    .message("用户不存在")
                    .codeN(Code.ERROR)
                    .build();
        }
        user.setPassword(passwordEncoder.encode(account));
        System.out.println("ok");
        userRepository.save(user);
    }

    @Transactional
    public void updateUserPasswordById(String uid,String password){
        log.debug(uid);
        log.debug(password);
        User user = userRepository.findById(uid).orElse(null);
        if(user == null){
            throw myException.builder()
                    .message("用户不存在")
                    .codeN(Code.ERROR)
                    .build();
        }
        user.setPassword(passwordEncoder.encode(password));
        userRepository.save(user);
    }

    public void addUser(User user){//管理员添加用户
        user.setPassword(passwordEncoder.encode(user.getAccount()));//以账号为设置密码
        userRepository.save(user);
    }


}
