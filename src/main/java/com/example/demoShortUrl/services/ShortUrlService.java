package com.example.demoShortUrl.services;

import com.example.demoShortUrl.domain.ShortUrl;

import java.util.List;

public interface ShortUrlService {

    List<ShortUrl> getAllShortUrls();

    String generateShortUrl(String longUrl);

    String getLongUrlByShortUrl(String shortUrl);


}
