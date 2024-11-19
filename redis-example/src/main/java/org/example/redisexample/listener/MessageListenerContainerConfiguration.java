
package org.example.redisexample.listener;

import org.example.redisexample.dto.Order;
import jakarta.annotation.PreDestroy;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.stream.Consumer;
import org.springframework.data.redis.connection.stream.ObjectRecord;
import org.springframework.data.redis.connection.stream.ReadOffset;
import org.springframework.data.redis.connection.stream.StreamOffset;
import org.springframework.data.redis.stream.StreamMessageListenerContainer;

import java.time.Duration;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

@Configuration
@RequiredArgsConstructor
@Slf4j
public class MessageListenerContainerConfiguration {

    private final RedisConnectionFactory redisConnectionFactory;
    private final OrderMessageListener listener;
    private final ExecutorService executor =  Executors.newSingleThreadExecutor();

    @PreDestroy
    public void destroyExecutor() throws InterruptedException {
        executor.shutdown();
        if (executor.awaitTermination(2, TimeUnit.SECONDS)) {
            log.debug("executor.awaitTermination");
        }
    }
    @Bean
    public StreamMessageListenerContainer<String, ObjectRecord<String, String>> register() {
        // 注册监听器
        StreamMessageListenerContainer.StreamMessageListenerContainerOptions<String, ObjectRecord<String, String>> options =
                StreamMessageListenerContainer.StreamMessageListenerContainerOptions
                        .builder()
                        .executor(executor)
                        .pollTimeout(Duration.ofMillis(100))
                        .targetType(String.class).build();
        StreamMessageListenerContainer<String, ObjectRecord<String, String>> container = StreamMessageListenerContainer.create(redisConnectionFactory, options);


        // 消费组名称，消费者名称
        container.receive(Consumer.from(Order.GROUP_NAME, Order.GROUP_CONSUMER), StreamOffset.create(Order.STREAM_KEY, ReadOffset.lastConsumed()), listener);

        container.start();
        return container;
    }
}
