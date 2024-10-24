package org.example.springmvcexample.component;

import com.auth0.jwt.interfaces.DecodedJWT;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.springmvcexample.excepiton.Code;
import org.example.springmvcexample.excepiton.myException;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;


@Slf4j
@Component
@RequiredArgsConstructor
public class LoginInterceptor implements HandlerInterceptor {

    private final JWTComponent jwtComponent;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader("token");
        if(token == null){
            throw myException.builder().code(Code.UNAUTHORIZED).build();
        }

        DecodedJWT decodedJWT = jwtComponent.decode(token);
        String name = decodedJWT.getClaim("name").asString();
        String role = decodedJWT.getClaim("role").asString();

        request.setAttribute("name", name);
        request.setAttribute("role", role);

        return true;
    }
}
