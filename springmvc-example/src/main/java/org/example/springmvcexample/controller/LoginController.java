package org.example.springmvcexample.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.springmvcexample.component.JWTComponent;
import org.example.springmvcexample.dox.User;
import org.example.springmvcexample.dox.User01;
import org.example.springmvcexample.excepiton.Code;
import org.example.springmvcexample.service.UserService;
import org.example.springmvcexample.vo.ResultVO;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/")
@Tag(name="登录")
public class LoginController {

    private final JWTComponent jwtComponent;
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    @PostMapping("login")
    public ResultVO login( @RequestBody User user,HttpServletResponse response){//把请求体的json反序列化为user对象传入使用
        //想来登录时，用登录信息试图从数据库中取User
        User userR = userService.getUserByAccount(user.getAccount());
        System.out.println(userR.toString());
        if(userR == null || !passwordEncoder.matches(user.getPassword(), userR.getPassword())) {
            return ResultVO.error(Code.LOGIN_ERROR);
        }
        String token = jwtComponent.encode(
                Map.of("uid", userR.getId(),
                        "role", userR.getRole())
        );
        response.setHeader("token", token);
        response.setHeader("role", userR.getRole());
        //给前端Header返回token和role,role可以是自己写的
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


    @PostMapping("login1")
    public ResultVO login1(@RequestBody User01 user01){//来了一个登录，先把请求体里的 json 转化为 user对象
        //根据用户名查询这个对象是否存在
        User01 u = userService.getUser01(user01.getUserName());
        //存在则拿到数据库中的 user对象，不存在则登陆失败
        //还有一种情况，用户存在，但是传过来的密码跟数据库里的不一样
        if(u == null || !passwordEncoder.matches(user01.getPassword(),u.getPassword())){
            log.debug("登陆失败");
            return ResultVO.error(Code.LOGIN_ERROR);
        }
        log.debug("登陆成功");
        return ResultVO.success(u);
    }

    @PostMapping("login2")
    public ResultVO login2(@RequestBody User01 user01, HttpServletResponse response){
        User01 user = userService.getUser01(user01.getUserName());
        if(user == null || !passwordEncoder.matches(user01.getPassword(),user.getPassword())){
            return ResultVO.error(Code.LOGIN_ERROR);
        }
        String result = jwtComponent.encode(Map.of("name",user.getUserName(),"role",user.getRole()));
        //获取加密后的 token
        log.debug(result);
        //把 result 塞入 请求的header中 作为token
        response.addHeader("key",result);
        response.addHeader("role",user.getRole());

        return ResultVO.success(user);
    }

    @PostMapping("welcome")
    public ResultVO welcome(@RequestAttribute("role")String role){
        log.debug(role);
        return ResultVO.success(Map.of("msg","欢迎"));
    }

}



