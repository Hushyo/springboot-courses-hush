package org.example.springmvcexample.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ResultVO {
    private int code;
    private String message;
    private Object data;
    //业务成功则返回数据和成功码封装成的VO对象
    public static ResultVO success(Object data){
        return ResultVO.builder()
                .data(data)
                .code(200)
                .build();
    }
    private static final ResultVO EMPTY = ResultVO.builder().code(200).data(Map.of()).build();
    //创建并藏起来一个空的VO对象，使得下面的方法不用每次都创建一个VO对象
    //用于统一返回一个不需要数据的VO对象
    public static ResultVO ok(){
        return EMPTY;
    }
}
