package org.example.redisexample.service;

import org.example.redisexample.dto.Item;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Random;
import java.util.concurrent.CountDownLatch;


@SpringBootTest
@Slf4j
class OrderServiceTest {

    @Autowired
    private OrderService orderService;

    @Test
    void addItemsTest() {
        List<Item> items = List.of(
                Item.builder().id("01JCN373YCAV5PPFXP5J66R2P3").total(30).build(),
                Item.builder().id("01JCN373YCVXX26QMXAJKX1701").total(30).build()
        );
        orderService.addItems(items);
    }

    @Test
    void buy() throws InterruptedException {
        int count = 200;
        Random random = new Random();
        CountDownLatch latch = new CountDownLatch(count);
        for (int i = 0; i < 200; i++) {
            Thread.startVirtualThread(()->{
                long buy = orderService.buy("01JCN373YCAV5PPFXP5J66R2P3",String.valueOf(random.nextInt(count)));
                log.debug("{}",buy);
                latch.countDown();

            });
        }
        latch.await();
    }
/*
    @Test
    void rushBuyTest() throws InterruptedException {
        // 模拟抢购的商品，抢购用户
        Item item = Item.builder().id("01HN7JNHG93N3RSPF60MEG4WE2").build();
        final int AMOUNT = 200;
        CountDownLatch latch = new CountDownLatch(AMOUNT);
        var random = new Random();
        for (int i = 0; i < AMOUNT; i++) {
            Thread.startVirtualThread(() -> {
                long quantity = orderService.rushBuy(item, String.valueOf(random.nextInt(100)));
                log.debug("剩余数量：{}", quantity);
                latch.countDown();
            });
        }
        latch.await();
    }
    */
}