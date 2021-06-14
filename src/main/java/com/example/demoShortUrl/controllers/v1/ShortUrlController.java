package com.example.demoShortUrl.controllers.v1;

import com.example.demoShortUrl.domain.ShortUrl;
import com.example.demoShortUrl.repositories.ShortUrlRepository;
import com.example.demoShortUrl.services.ShortUrlService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller  //Spring MVC Controller - this is a spring managed controller.
@RequestMapping("/api/v1/shorturls")
public class ShortUrlController {   //when spring initiates this it will inject instance of repository.

    private final ShortUrlService shortUrlService;

    public ShortUrlController(ShortUrlService shortUrlService) {
        this.shortUrlService = shortUrlService;
    }

    @GetMapping("/{url}")
    public ResponseEntity<String> getLongUrlByShortUrl(@PathVariable String url)
    {
        return new ResponseEntity<String>(
                shortUrlService.getLongUrlByShortUrl(url), HttpStatus.OK
        );
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<String> generateShortUrl(@RequestBody String longUrl){
        return new ResponseEntity<String>( shortUrlService.generateShortUrl(longUrl),
                HttpStatus.CREATED);
    }

}