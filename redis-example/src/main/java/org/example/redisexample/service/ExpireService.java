package org.example.redisexample.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.FunctionMode;
import org.redisson.api.FunctionResult;
import org.redisson.api.RedissonClient;
import org.redisson.client.codec.IntegerCodec;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class ExpireService {
        private final RedissonClient client;

        public boolean expire(String key,int expire,int count){
            return client.getFunction(IntegerCodec.INSTANCE)
                    .call(FunctionMode.WRITE,
                            "expireAPI_37",
                            FunctionResult.BOOLEAN,
                            List.of(key),
                            expire,
                            count);
        }
}
