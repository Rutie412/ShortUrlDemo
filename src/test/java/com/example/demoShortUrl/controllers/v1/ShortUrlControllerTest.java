package com.example.demoShortUrl.controllers.v1;

import com.example.demoShortUrl.domain.ShortUrl;
import com.example.demoShortUrl.services.ShortUrlService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDateTime;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class ShortUrlControllerTest {

    @InjectMocks
    ShortUrlController shortUrlController;

    @Mock
    ShortUrlService shortUrlService;

    MockMvc mockMvc;

    public static final Long ID = 111L;
    public static final String LONG_URL = "https://www.ynet.co.il/home/0,7340,L-8,00.html";
    public static final String SHORT_URL = "A1S2d3f4";


    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(shortUrlController).build();
    }

    @Test
    void getLongUrlByShortUrl() throws Exception {
        ShortUrl sUrl1 = new ShortUrl();
        sUrl1.setId(ID);
        sUrl1.setLongUrl(LONG_URL);
        sUrl1.setShortGeneratedUrl(SHORT_URL);
        sUrl1.setExpireDate(LocalDateTime.now().plusDays(90));

        when(shortUrlService.getLongUrlByShortUrl(anyString())).thenReturn(sUrl1.getLongUrl());

        mockMvc.perform(get("/api/v1/shorturls/" + SHORT_URL)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", equalTo(LONG_URL)));
    }




}