package com.example.demoShortUrl.repositories;

import com.example.demoShortUrl.domain.ShortUrl;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ShortUrlRepository extends JpaRepository<ShortUrl, Long> {
   // ShortUrl findByShortUrl(String shortUrl);

    @Query("SELECT t FROM ShortUrl t WHERE t.longUrl = ?1")
    ShortUrl findByLongUrl(String longUrl);
}
