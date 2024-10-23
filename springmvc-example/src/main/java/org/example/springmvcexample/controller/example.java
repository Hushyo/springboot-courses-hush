package org.example.springmvcexample.controller;

import lombok.RequiredArgsConstructor;
import org.example.springmvcexample.dox.Address;
import org.example.springmvcexample.service.UserService;
import org.example.springmvcexample.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RequestMapping("/api/")
@RestController
@RequiredArgsConstructor
public class example {

    private final UserService userService;

    @GetMapping("address")
    public ResultVO getAddress(){
        userService.readFile();
        return ResultVO.success(200);
    }
}
