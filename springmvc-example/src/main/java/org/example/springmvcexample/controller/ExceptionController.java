package org.example.springmvcexample.controller;


import lombok.extern.slf4j.Slf4j;
import org.example.springmvcexample.excepiton.Code;
import org.example.springmvcexample.excepiton.myException;
import org.example.springmvcexample.vo.ResultVO;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class ExceptionController {
    @ExceptionHandler(myException.class)
    public ResultVO handlerMyException(myException e) {
        if(e.getCode()!=null) {
            return ResultVO.error(e.getCode());
        }
        return ResultVO.error(e.getCodeN(),e.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public ResultVO exceptionHandler(Exception e) {
        return ResultVO.error(Code.ERROR,e.getMessage());
    }
}
