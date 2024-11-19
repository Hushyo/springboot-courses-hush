package org.example.redisexample.service;

import lombok.extern.slf4j.Slf4j;
import org.example.redisexample.component.ULID;
import org.junit.jupiter.api.Test;
import org.redisson.api.RBucket;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@Slf4j
class ExpireServiceTest {

    @Autowired
    private ExpireService expireService;
    private ULID ulid;
    private RedissonClient client;

    @Test
    void expire() throws InterruptedException {
        for(int i=0; i<10; i++) {
            boolean expire = expireService.expire("api:666",10,2);
            log.debug("{}",expire);
            TimeUnit.SECONDS.sleep(1);
        }

    }


}