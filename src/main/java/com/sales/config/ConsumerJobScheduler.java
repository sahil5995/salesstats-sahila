package com.sales.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Component
public class ConsumerJobScheduler {

    @Autowired
    private SharedDS sharedDS;

    /**
     * This is job scheduler method, which is called after every second.
     * It is responsible for taking data from Queue and putting in Hashmap.
     */
    @Scheduled(initialDelay = 1000, fixedDelay = 1000)
    public void consumeQueue() {

        List<Double> list = new ArrayList<>(100);

        if (!sharedDS.getQueue().isEmpty()) {
            sharedDS.getQueue().drainTo(list);
        }

        sharedDS.getMap().put(LocalDateTime.now().getSecond(), list);
        //System.out.println("------------------------------" + LocalDateTime.now().getSecond());
        //System.out.println("-------- Added scheduler:"+sharedDS.getQueue().size());
    }

}
