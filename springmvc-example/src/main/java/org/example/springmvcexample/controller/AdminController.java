package org.example.springmvcexample.controller;


import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.springmvcexample.service.UserService;
import org.example.springmvcexample.vo.ResultVO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/admin/")
@RequiredArgsConstructor//用于注入其他组件
@Tag(name="登录")
public class AdminController {

    private final UserService userService;

    @GetMapping("users")
    public ResultVO getUsers() {
        return ResultVO.success(userService.listUsers());
    }

    @GetMapping("users/{account}")
    public ResultVO getUser(@PathVariable String account) {
        return ResultVO.success(userService.getUserByAccount(account));
    }
}
