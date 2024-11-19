package org.example.redisexample;

import lombok.extern.slf4j.Slf4j;
import org.example.redisexample.component.ULID;
import org.example.redisexample.dto.Order;
import org.junit.jupiter.api.Test;
import org.redisson.api.RBucket;
import org.redisson.api.RMap;
import org.redisson.api.RedissonClient;
import org.redisson.client.codec.StringCodec;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.convert.Bucket;

import java.sql.SQLOutput;

@SpringBootTest
@Slf4j
public class RedissonTest {
    @Autowired
    private RedissonClient client;
    @Autowired
    private ULID u;

    @Test
    public void testRedisson() {
        String name = "hush";
        String key = "users:14";
        RBucket<String> bucket = client.getBucket(key, StringCodec.INSTANCE);
        log.info("bucket:{}", bucket.isExists());
        bucket.set(name);
        RBucket<String> bucket2 = client.getBucket(key, StringCodec.INSTANCE);
        log.info("bucket2:{}", bucket2);
    }
    @Test
    void test(){
    var orderId= u.nextULID();
    var key = Order.PRE_KEY+orderId;
      RBucket<Object> bucket = client.getBucket(key);
      bucket.set(Order.builder().id(orderId).userId("1").build());
      RBucket<Object> bucket2 = client.getBucket(key);
      log.debug("{}", bucket2.get());
    }
}
