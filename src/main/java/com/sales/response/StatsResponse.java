package com.sales.response;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Locale;


@Getter
@Setter
public class StatsResponse {

    private String total_sales_amount;
    private String average_amount_per_order;


    public StatsResponse(double total_sales_amount,double average_amount_per_order ){
        this.total_sales_amount = String.format(Locale.US, "%.2f", total_sales_amount);
        this.average_amount_per_order = String.format(Locale.US, "%.2f", average_amount_per_order);
    }
}
