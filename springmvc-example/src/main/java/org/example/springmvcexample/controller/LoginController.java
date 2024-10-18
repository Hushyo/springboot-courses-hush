package org.example.springmvcexample.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.springmvcexample.dox.User;
import org.example.springmvcexample.excepiton.Code;
import org.example.springmvcexample.service.UserService;
import org.example.springmvcexample.vo.ResultVO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController //所有方法返回值序列化json字段后返回
@RequiredArgsConstructor //构造函数注入组件
@RequestMapping("/api/") //声明请求前缀
public class LoginController {

    private final UserService userService;

    @PostMapping("login")
    public ResultVO login(@RequestBody User user){
        //把请求体的json反序列化为user对象传入使用

        User userR = userService.getUserByAccount(user.getAccount(),user.getPassword());
        //想来登录时，用登录信息试图从数据库中取User
        if(userR == null){
            //用户不存在，返回一个通用的错误信息
            return ResultVO.error(Code.LOGIN_ERROR);
        }
        //成功则把 userR 对象作为数据 存入成功信息里返回
        return ResultVO.success(userR);
    }
}
