package org.example.backendexample.controller;

import lombok.RequiredArgsConstructor;
import org.example.backendexample.dox.User;
import org.example.backendexample.service.UserService;
import org.example.backendexample.vo.ResultVO;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user/")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    @PatchMapping("password")
    public ResultVO patchPassword(@RequestBody User user, @RequestAttribute("uid") String uid) {
        userService.updateUserPasswordById(uid, user.getPassword());
        return ResultVO.ok();
    }

    @GetMapping("users")
    public ResultVO getUsers() {
        return ResultVO.success(userService.listUsers());
    }
}
