package org.example.springmvcexample.component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.example.springmvcexample.dox.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@Slf4j
class JWTComponentTest {

    @Autowired
    private JWTComponent jwtComponent;

    @Test
    public void test()  throws InterruptedException {
        String token = jwtComponent.encode(Map.of("uid",123456,"role",9));
        log.debug(token);
        log.debug("{}",token.length());

        long uid = jwtComponent.decode(token).getClaim("uid").asLong();
        log.debug("{}",uid);

        Thread.sleep(2000);
        long uid2 = jwtComponent.decode(token).getClaim("uid").asLong();
        log.debug("{}",uid2);
    }


    @Autowired
    private ObjectMapper objectMapper;
    @Test
    public void test_mapper(){
        User u = User.builder()
                .name("pang")
                .password("123456")
                .build();

        Map<String ,User> map=Map.of("user",u);
        try {
            String json = objectMapper.writeValueAsString(map);
            log.debug(json);
            Map<String ,User> reMap = objectMapper.readValue(json, new TypeReference<Map<String ,User>>(){});
            reMap.forEach((k,v)->{
                log.debug(k);
                log.debug(v.toString());
            });
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}