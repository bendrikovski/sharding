package com.ben.task.ShardingProject.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import com.ben.task.ShardingProject.services.PaymentService;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = PaymentController.class)
public class PaymentControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private PaymentService paymentService;

    @Test
    public void whenNullValueThenReturns400() throws Exception {
        String query = "[" + "{ \"sender_id\": \"1\", \"recipient_id\": \"234\", \"amount\": \"\" }]";
        mockMvc.perform(post("/payments/save")
                .contentType(MediaType.APPLICATION_JSON)
                .content(query))
                .andExpect(status().isBadRequest());
    }
    @Test
    public void whenRightValueThenReturns200() throws Exception {
        String query = "[" + "{ \"sender_id\": \"1\", \"recipient_id\": \"234\", \"amount\": \"12\" }]";
        mockMvc.perform(post("/payments/save")
                .contentType(MediaType.APPLICATION_JSON)
                .content(query))
                .andExpect(status().isOk());
    }
}