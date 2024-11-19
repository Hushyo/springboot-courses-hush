package org.example.backendexample.controller;


import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.example.backendexample.excepiton.Code;
import org.example.backendexample.excepiton.myException;
import org.example.backendexample.vo.ResultVO;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

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
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResultVO handlerNotValidException(MethodArgumentNotValidException exception) {
        StringBuilder stringBuilder = new StringBuilder();
        exception.getBindingResult()
                .getFieldErrors()
                .forEach(error->{
                    stringBuilder.append(error.getField());
                    stringBuilder.append(" : ");
                    stringBuilder.append(error.getDefaultMessage());
                    stringBuilder.append(";");
                });
        return ResultVO.error(400,stringBuilder.toString());
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResultVO handlerTypeMismatchException(MethodArgumentTypeMismatchException exception, HttpServletRequest request) {
        String msg = request.getRequestURI()+":"+"请求地址参数"+exception.getValue()+"错误";
        return ResultVO.error(400,msg);
    }

    @ExceptionHandler(InvalidFormatException.class)
    public ResultVO handlerInvalidFormatException(InvalidFormatException exception) {
        StringBuilder stringBuilder = new StringBuilder();
        exception.getPath()
                .forEach(p->{
                    stringBuilder.append("属性");
                    stringBuilder.append(p.getFieldName());
                    stringBuilder.append(" ,输入值为: ").append(exception.getValue());
                    stringBuilder.append(",类型错误！");
                });
        return ResultVO.error(400,stringBuilder.toString());
    }

    @ExceptionHandler(Exception.class)
    public ResultVO exceptionHandler(Exception e) {
        return ResultVO.error(Code.ERROR,e.getMessage());
    }
}
