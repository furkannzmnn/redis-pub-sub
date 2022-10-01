package com.redis.redispubsub;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class RedisPubSubApplicationTests  extends BaseITTest{

    private String session;
    @BeforeEach
    public void add() {
        session = login();
    }
    @Test
    void contextLoads() {
    }

}
