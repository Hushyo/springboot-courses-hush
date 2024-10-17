package org.example.springmvcexample.controller;

import org.example.springmvcexample.dox.Address;
import org.example.springmvcexample.vo.ResultVO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RequestMapping("/api/")
@RestController
public class example {
    @GetMapping("address")
    public ResultVO getAddress(){
        return ResultVO.success(Map.of("apple",1));
    }
}
