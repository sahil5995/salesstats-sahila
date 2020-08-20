package com.sales.service;

import com.sales.config.SharedDS;
import com.sales.response.StatsResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SalesService {

    @Autowired
    private SharedDS sharedDS;

    /**
     * This method is called by the API and is used to put data in Queue
     *
     * @param salemAmount order amount
     */
    public void addSalesData(double salemAmount) {
        sharedDS.getQueue().add(salemAmount);
        //System.out.println("-------- Added service:" + sharedDS.getQueue().size());
    }

    /**
     * This method is used to supply the stats data
     *
     * @return StatsResponse Stats data
     */
    public StatsResponse getStatsData() {
        List<Double> list = sharedDS.getMap().values().stream()
                .flatMap(Collection::stream)
                .collect(Collectors.toList());

        double sum = list.stream().mapToDouble(i -> i).sum();
        double average = list.stream().mapToDouble(i -> i).average().orElse(0.00);

        return new StatsResponse(sum, average);
    }

}
