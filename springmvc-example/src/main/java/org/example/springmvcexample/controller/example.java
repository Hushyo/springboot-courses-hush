package org.example.springmvcexample.controller;

import org.example.springmvcexample.dox.Address;
import org.example.springmvcexample.vo.ResultVo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/")
@RestController
public class example {
    @GetMapping("address")
    public ResultVo getAddress(){
        return ResultVo.builder()
                .code(200)
                .data(Address.builder().id("1").detail("地址").build())
                .build();
    }
}
