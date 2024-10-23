package org.example.springmvcexample.component;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.SignatureGenerationException;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.DecodedJWT;
import jakarta.annotation.PostConstruct;
import org.example.springmvcexample.excepiton.Code;
import org.example.springmvcexample.excepiton.myException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.Map;

@Component
public class JWTComponent {
    //JWT 组件，用于创建 token
    //JWT Json Web Token 缩写
    //具体 token 怎么创建，需要自己写方法
    @Value("${my.secretkey}")
    private String secretkey;
    private Algorithm algorithm;
    @PostConstruct
    private void init() {
        algorithm = Algorithm.HMAC256(secretkey);
    }

    public String encode(Map<String,Object> map){
        LocalDateTime time = LocalDateTime.now().plusDays(1);

        return JWT.create()
                .withPayload(map)
                .withIssuedAt(new Date())
                .withExpiresAt(Date.from(time.atZone(ZoneId.systemDefault()).toInstant()))
                .sign(algorithm);
    }

    public DecodedJWT decode(String token){
        try{
            return JWT.require(algorithm).build().verify(token);
        }catch(TokenExpiredException | SignatureVerificationException e){
            if(e instanceof SignatureVerificationException){
                throw myException.builder().code(Code.FORBIDDEN).build();
            }
            throw myException.builder().code(Code.TOKEN_EXPIRED).build();
        }
    }
}
