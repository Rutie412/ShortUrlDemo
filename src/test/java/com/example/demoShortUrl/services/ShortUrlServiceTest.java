package com.example.demoShortUrl.services;

import com.example.demoShortUrl.domain.ShortUrl;
import com.example.demoShortUrl.repositories.ShortUrlRepository;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;//when;
//import static org.mockito.Mockito.thenRe;

@ExtendWith(MockitoExtension.class)
class ShortUrlServiceTest {

    public static final Long ID = 111L;
    public static final String LONG_URL = "https://www.ynet.co.il/home/0,7340,L-8,00.html";
    public static final String SHORT_URL = "A1S2d3f4";

    ShortUrlService shortUrlService;

    @Mock
    ShortUrlRepository shortUrlRepository;

    @BeforeEach
    void setUp() {
        //MockitoAnnotations.initMocks(this);
        shortUrlService = new ShortUrlServiceImpl(shortUrlRepository);
    }

    @Test
    void getAllShortUrls() {
        List<ShortUrl> shortUrls = Arrays.asList(new ShortUrl(), new ShortUrl(), new ShortUrl());

        when(shortUrlRepository.findAll()).thenReturn(shortUrls);

        List<ShortUrl> shortUrlDTOs = shortUrlService.getAllShortUrls();

        assertEquals(3, shortUrlDTOs.size());
    }

    @Test
    void generateShortUrl() {
        String shortUrlStr = shortUrlService.generateShortUrl(LONG_URL);
        String LongUrlFromService = shortUrlService.getLongUrlByShortUrl(shortUrlStr);

        assertEquals(LONG_URL, LongUrlFromService);

        String shortUrlStr2 = shortUrlService.generateShortUrl(LONG_URL);
        assertEquals(shortUrlStr, shortUrlStr2);
    }

    @Test
    void getLongUrlByShortUrl() {
        ShortUrl sUrl1 = new ShortUrl();
        sUrl1.setId(ID);
        sUrl1.setLongUrl(LONG_URL);
        sUrl1.setShortGeneratedUrl(SHORT_URL);
        sUrl1.setExpireDate(LocalDateTime.now().plusDays(90));

        when(shortUrlRepository.findById(anyLong())).thenReturn(java.util.Optional.of(sUrl1));

        String longUrlStr = shortUrlService.getLongUrlByShortUrl(SHORT_URL);

        assertEquals(LONG_URL, longUrlStr);
    }
}