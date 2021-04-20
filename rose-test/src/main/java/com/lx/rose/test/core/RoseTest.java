package com.lx.rose.test.core;

import com.lx.rose.retry.core.exception.retry.RetryException;
import com.lx.rose.retry.core.retry.IRetry;
import org.springframework.stereotype.Service;

@Service
public class RoseTest {

    public void test() throws RetryException, InterruptedException {
        IRetry.getInstance().doRetry(() -> {
            double random = Math.random();
            if (random!=1) {
                throw new RuntimeException("test error");
            }
            return random;
        }).execute();
    }

}
