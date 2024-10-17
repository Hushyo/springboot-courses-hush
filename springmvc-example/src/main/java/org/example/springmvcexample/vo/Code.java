package org.example.springmvcexample.vo;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Code {
    SUCCESS(200,"成功"),
    BAD_REQUEST(Code.ERROR,"请求错误"),
    LOGIN_ERROR(Code.ERROR,"用户名或密码错误");

    public static final int ERROR = 400;
    private final int code;
    private final String message;
}
