package org.example.redisexample.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.redisexample.component.ULID;
import org.example.redisexample.dto.Item;
import org.example.redisexample.dto.Order;
import org.redisson.api.*;
import org.redisson.api.stream.StreamAddArgs;
import org.redisson.client.codec.IntegerCodec;
import org.redisson.client.codec.StringCodec;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class OrderService {
    private final ULID ulid;
    private final RedissonClient client;

    public void addItems(List<Item> Items){
        RBatch batch =  client.createBatch();
        for(Item item : Items){
           RMapAsync<String,Integer> map = batch.getMap(Item.PRE_KEY+item.getId(), IntegerCodec.INSTANCE);
            map.putAsync("total",item.getTotal());
        }
        batch.execute();
    }

    public long buy(String itemId,String userId){
        long q =client.getFunction().call(FunctionMode.WRITE,
                "buy_37",
                FunctionResult.LONG,
                List.of(Item.PRE_KEY+itemId));
        if(q == -1){
            log.debug("over");
            return -1;
        }
        String id = ulid.nextULID();
        Order o =Order.builder()
                .id(id)
                .userId(userId)
                .itemId(itemId)
                .build();
        client.getBucket(Order.PRE_KEY+id).set(o);
        sendMessage(o);
        return q;
    }

    public void sendMessage(Order order){
        client.getStream(Order.STREAM_KEY,StringCodec.INSTANCE)
                .add(StreamAddArgs.entry("orderid",order.getId()));
    }
}
