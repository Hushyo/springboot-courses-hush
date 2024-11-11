package org.example.springmvcexample.controller;


import org.example.springmvcexample.vo.ResultVO;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/user/")
public class UserController {
    @GetMapping("info")
    public ResultVO getInfo(@RequestAttribute("uid") String uid) {
        return ResultVO.success(Map.of("uid",uid));
    }

}
