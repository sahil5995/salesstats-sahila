package com.sales.service;

import com.sales.config.SharedDS;
import com.sales.response.StatsResponse;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class SalesServiceTest {

    @InjectMocks
    private SalesService salesService;

    @Mock
    private SharedDS sharedDS;

    @Test
    public void testAddSalesData() {

        //Arrange
        Mockito.when(sharedDS.getQueue()).thenReturn(new LinkedBlockingQueue<>());

        //Act
        salesService.addSalesData(2.0);

        //Assert
        verify(sharedDS, atLeastOnce()).getQueue();
        assertEquals(sharedDS.getQueue().size(), 1);
    }

    @Test
    public void testGetStatsDataWithSalesData() {

        //Arrange
        HashMap<Integer, List<Double>> map = new HashMap<>();
        map.put(1, Arrays.asList(1.0, 2.0));
        map.put(2, Arrays.asList(3.0, 4.0));
        Mockito.when(sharedDS.getMap()).thenReturn(map);

        //Act
        StatsResponse response = salesService.getStatsData();

        //Assert
        assertEquals("10.00", response.getTotal_sales_amount());
        assertEquals("2.50", response.getAverage_amount_per_order());
    }

    @Test
    public void testGetStatsDataWithoutSalesData() {

        //Arrange
        HashMap<Integer, List<Double>> map = new HashMap<>();
        Mockito.when(sharedDS.getMap()).thenReturn(map);

        //Act
        StatsResponse response = salesService.getStatsData();

        //Assert
        assertEquals("0.00", response.getTotal_sales_amount());
        assertEquals("0.00", response.getAverage_amount_per_order());
    }

}
