package org.example.springmvcexample.interceptor;

import com.auth0.jwt.interfaces.DecodedJWT;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.springmvcexample.component.JWTComponent;
import org.example.springmvcexample.excepiton.Code;
import org.example.springmvcexample.excepiton.myException;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;


@Slf4j
@Component
@RequiredArgsConstructor
public class LoginInterceptor implements HandlerInterceptor {
    //注入token解密和校验的组件
    private final JWTComponent jwtComponent;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader("token");
        //验证用户是否登陆过，先拿 token
        if(token == null){//为空说明没登陆过，抛异常
            throw myException.builder().code(Code.UNAUTHORIZED).build();
        }
        //存在则从token中拿信息，先解构
        DecodedJWT decodedJWT = jwtComponent.decode(token);
        String name = decodedJWT.getClaim("name").asString();
        String role = decodedJWT.getClaim("role").asString();
        //由于login时，token里map对象是以 uid 为键塞得用户id，所以这里根据uid取
        String uid = decodedJWT.getClaim("uid").asString();

        //把这些信息塞到 request里往下传
        request.setAttribute("name", name);
        request.setAttribute("role", role);
        request.setAttribute("uid", uid);

        return true;
    }
}
