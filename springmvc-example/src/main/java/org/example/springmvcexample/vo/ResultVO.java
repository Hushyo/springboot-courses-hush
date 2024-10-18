package org.example.springmvcexample.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.springmvcexample.excepiton.Code;

import java.util.Map;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ResultVO {

    private int code;//业务码
    private String message; //异常信息，成功时不需要这个属性
    private Object data; //返回数据，不知道返回的数据是什么类型，用Object可以接收全部

    //业务成功则 返回数据和成功码封装成的VO对象
    //把构造封装起来，对外暴露工具类
    public static ResultVO success(Object data){
        return ResultVO.builder()
                .data(data)
                .code(200)
                .build();
    }

    public static ResultVO error(Code code){
    return ResultVO.builder()
            .code(code.getCode())
            .message(code.getMessage())
            .build();
    }
    //如果手动传入异常码和异常信息则调用这个
    public static ResultVO error(int code,String message){
        return ResultVO.builder()
                .code(code)
                .message(message)
                .build();
    }

    private static final ResultVO EMPTY = ResultVO.builder().code(200).build();
    //用于返回一个不需要数据的VO对象，比如 post 请求成功，并不需要返回数据，只需要知道成功了
    //每次都创建一个成功对象，虽然里面只塞了一个 200成功码，但是仍然浪费
    //所以内部封装一个空容器，而不是创建
    public static ResultVO ok(){
        return EMPTY;
    }
}
