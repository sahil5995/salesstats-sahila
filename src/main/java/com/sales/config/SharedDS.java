package com.sales.config;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;

@Component
public class SharedDS {

    private LinkedBlockingQueue<Double> queue = new LinkedBlockingQueue<>();
    private HashMap<Integer, List<Double>> map = new HashMap<>();

    public HashMap<Integer, List<Double>> getMap() {
        return map;
    }

    public LinkedBlockingQueue<Double> getQueue() {
        return queue;
    }

}
