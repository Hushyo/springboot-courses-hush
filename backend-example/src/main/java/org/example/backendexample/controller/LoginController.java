package org.example.backendexample.controller;

import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.backendexample.component.JWTComponent;
import org.example.backendexample.dox.User;
import org.example.backendexample.excepiton.Code;
import org.example.backendexample.service.UserService;
import org.example.backendexample.vo.ResultVO;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/")
public class LoginController {

    private final JWTComponent jwtComponent;
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    @PostMapping("login")
    public ResultVO login( @RequestBody User user,HttpServletResponse response){//把请求体的json反序列化为user对象传入使用
        //登录时，从数据库中取User
        User userR = userService.getUser(user.getAccount());
        if(userR == null || !passwordEncoder.matches(user.getPassword(), userR.getPassword())) {
            return ResultVO.error(Code.LOGIN_ERROR);
        }
        String token = jwtComponent.encode(
                Map.of("uid", userR.getId(),
                        "role", userR.getRole())
        );
        response.setHeader("token", token);
        response.setHeader("role", userR.getRole());
        //登陆后给Header返回token和role, token用签发的，role可以是自己随意写的
        return ResultVO.success(userR);
    }

    /*
    @Operation(summary = "普通登录",description = "普通登陆方法")
    @PostMapping("login")
    public ResultVO login( @RequestBody User user){//把请求体的json反序列化为user对象传入使用
        //想来登录时，用登录信息试图从数据库中取User
        User userR = userService.getUserByAccount(user.getAccount(),user.getPassword());
        if(userR == null){//用户不存在，返回一个通用的错误信息
            return ResultVO.error(Code.LOGIN_ERROR);
        }
        //成功则把 userR 对象作为数据 存入成功信息里返回
        return ResultVO.success(userR);
    }*/

    @PostMapping("welcome")
    public ResultVO welcome(@RequestAttribute("role")String role){
        log.debug(role);
        return ResultVO.success(Map.of("msg","欢迎"));
    }

}



