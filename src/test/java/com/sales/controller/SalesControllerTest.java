package com.sales.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.hamcrest.CoreMatchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@AutoConfigureMockMvc
@SpringBootTest
public class SalesControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testSuccessSales() throws Exception {

        //Act and Assert
        mockMvc.perform(post("/api/sales").contentType(MediaType.APPLICATION_FORM_URLENCODED_VALUE)
                .content("sales_amount=2.0")).
                andDo(print()).andExpect(status().isAccepted());

    }

    @Test
    public void testArgNotPresentSales() throws Exception {

        //Act and Assert
        mockMvc.perform(post("/api/sales").contentType(MediaType.APPLICATION_FORM_URLENCODED_VALUE)).
                andDo(print()).andExpect(status().isBadRequest())
                .andExpect(status().
                        reason(containsString("Required Double parameter 'sales_amount' is not present")));

    }

    @Test
    public void testNumberFormatExceptionSales() throws Exception {

        //Act and Assert
        mockMvc.perform(post("/api/sales").contentType(MediaType.APPLICATION_FORM_URLENCODED_VALUE)
                .content("sales_amount=a")).
                andDo(print()).andExpect(status().isBadRequest())
                .andExpect(MockMvcResultMatchers.content().string("Number Format Error"));
    }

    @Test
    public void testSuccessStats() throws Exception {

        //Act and Assert
        mockMvc.perform(get("/api/statistics")).
                andDo(print()).andExpect(status().isOk()).
                andExpect(jsonPath("$.total_sales_amount").exists()).
                andExpect(jsonPath("$.average_amount_per_order").exists());
    }


}
