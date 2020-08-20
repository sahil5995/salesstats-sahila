package com.sales.config;

import org.awaitility.Duration;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.context.junit4.SpringRunner;

import static org.awaitility.Awaitility.await;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.verify;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ConsumerJobSchedulerTest {

    @SpyBean
    private ConsumerJobScheduler scheduler;

    @Test
    public void whenWaitOneSecond_thenScheduledIsCalledAtLeastTenTimes() {

        //Act and Assert
        await()
                .atMost(Duration.ONE_SECOND)
                .untilAsserted(() -> verify(scheduler, atLeast(1)).consumeQueue());
    }
}