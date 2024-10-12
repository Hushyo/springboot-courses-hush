package org.example.springmvcexample.vo;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ResultVo {

    private int code;
    private String message;
    private Object data;

    public static ResultVo success(Object data) {
        return ResultVo.builder().code(200).data(data).build();
    }
}
