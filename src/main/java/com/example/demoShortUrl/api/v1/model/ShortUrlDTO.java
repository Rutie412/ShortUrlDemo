package com.example.demoShortUrl.api.v1.model;

import java.time.LocalDateTime;

public class ShortUrlDTO {
    private Long id;
    private String longUrl;
    private String shortGeneratedUrl;
    private LocalDateTime expireDate;
}
