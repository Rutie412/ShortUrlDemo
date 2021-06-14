package com.example.demoShortUrl.bootstrap;

import com.example.demoShortUrl.domain.ShortUrl;
import com.example.demoShortUrl.repositories.ShortUrlRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class Bootstrap implements CommandLineRunner {

    private ShortUrlRepository shortUrlRepository;

    public Bootstrap(ShortUrlRepository shortUrlRepository) {
        this.shortUrlRepository = shortUrlRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        ShortUrl sUrl1 =// GenerateShortUrl("https://www.ynet.co.il/home/0,7340,L-8,00.html");
        new ShortUrl();//"https://www.ynet.co.il/home/0,7340,L-8,00.html",
        //LocalDateTime.now().plusDays(90));
        sUrl1.setId(111L);
        sUrl1.setLongUrl("https://www.ynet.co.il/home/0,7340,L-8,00.html");
        sUrl1.setExpireDate(LocalDateTime.now().plusDays(90));
        sUrl1.setShortGeneratedUrl("AAA");

        ShortUrl sUrl2 =// GenerateShortUrl("https://www.ynet.co.il/home/0,7340,L-8,00.html");
                new ShortUrl();//"https://www.ynet.co.il/home/0,7340,L-8,00.html",
        //LocalDateTime.now().plusDays(90));
        sUrl2.setId(222L);
        sUrl2.setLongUrl("https://www.ynet.co.il/home/0,7340,L-8,00.html");
        sUrl2.setExpireDate(LocalDateTime.now().plusDays(90));
        sUrl2.setShortGeneratedUrl("BBB");

        shortUrlRepository.save(sUrl1);
        shortUrlRepository.save(sUrl2) ;



        System.out.println("Started in Bootstrap");
        System.out.println("Number of short Urls: " + shortUrlRepository.count());
    }
}
