package org.example.backendexample.controller;

import lombok.RequiredArgsConstructor;
import org.example.backendexample.dox.User;
import org.example.backendexample.service.UserService;
import org.example.backendexample.vo.ResultVO;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin/")
@RequiredArgsConstructor
public class AdminController {
    private final UserService userService;
    @PostMapping("users")
    public ResultVO postUser(@RequestBody User user) {
        userService.addUser(user);
        return ResultVO.ok();
    }

    @PutMapping("users/{account}/password")
    public ResultVO putPassword(@PathVariable String account) {
        userService.updateUserPassword(account);
        return ResultVO.ok();
    }

    @GetMapping("users")
    public ResultVO getUsers() {
        return ResultVO.success(userService.listUsers());
    }

    @GetMapping("users/{userid}/local")
    public ResultVO get(@PathVariable int userid){
        return ResultVO.ok();
    }
}
