package com.org.test.application.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@SpringBootTest
@AutoConfigureMockMvc
class TestControllerTest {
    @Autowired
    private MockMvc mvc;
    @Autowired
    private WebApplicationContext ctx;
    @Autowired
    private ObjectMapper mapper;

    @BeforeEach
    @DisplayName("mvc 인코딩 설정")
    void setUp(){
        this.mvc = MockMvcBuilders
                .webAppContextSetup(ctx)
                .addFilters(new CharacterEncodingFilter("UTF-8",true))
                .alwaysDo(print())
                .build();
    }

    @Test
    @DisplayName("TEST")
    void transactionalTest() throws Exception {
        //given
        String url = "/test/transactionalTest";

        mvc.perform(MockMvcRequestBuilders.post(url)).andReturn(); //when
                //.andExpect(status().isOk())
                //.andExpect(jsonPath("$.payload").exists()); //then
    }

}