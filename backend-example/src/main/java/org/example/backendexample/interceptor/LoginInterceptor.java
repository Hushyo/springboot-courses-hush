package org.example.backendexample.interceptor;

import com.auth0.jwt.interfaces.DecodedJWT;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.backendexample.component.JWTComponent;
import org.example.backendexample.excepiton.Code;
import org.example.backendexample.excepiton.myException;
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
        //验证用户是否登陆过，拿token
        if(token == null){//为空说明没登陆过，抛异常
            throw myException.builder().code(Code.UNAUTHORIZED).build();
        }
        //存在则从token中拿信息
        DecodedJWT decodedJWT = jwtComponent.decode(token);
        String name = decodedJWT.getClaim("name").asString();
        String role = decodedJWT.getClaim("role").asString();
        String uid = decodedJWT.getClaim("uid").asString();

        //把这些信息塞到 request里
        request.setAttribute("name", name);
        request.setAttribute("role", role);
        request.setAttribute("uid", uid);

        return true;
    }
}
