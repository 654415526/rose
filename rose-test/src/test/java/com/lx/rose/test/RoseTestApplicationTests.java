package com.lx.rose.test;

import com.lx.rose.retry.core.exception.retry.RetryException;
import com.lx.rose.test.core.RoseTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class RoseTestApplicationTests {

    @Autowired
    private RoseTest roseTest;

    @Test
    void contextLoads() throws RetryException, InterruptedException {
        roseTest.test();
    }

}
