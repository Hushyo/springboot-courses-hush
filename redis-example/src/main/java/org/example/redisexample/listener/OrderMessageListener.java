package org.example.redisexample.listener;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.redisexample.dto.Order;
import org.springframework.data.redis.connection.stream.ObjectRecord;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.stream.StreamListener;
import org.springframework.stereotype.Component;


@Component
@Slf4j
@RequiredArgsConstructor
public class OrderMessageListener implements StreamListener<String, ObjectRecord<String, String>> {

    private final RedisTemplate<String, String> template;

    @Override
    public void onMessage(ObjectRecord<String, String> message) {
        log.debug("onMessage: mid: {} / orderid: {}", message.getId(), message.getValue());
        template.opsForStream().acknowledge(Order.GROUP_NAME, message);
    }
}
