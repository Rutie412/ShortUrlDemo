package com.example.demoShortUrl.services;

import com.example.demoShortUrl.domain.ShortUrl;
import com.example.demoShortUrl.repositories.ShortUrlRepository;
import com.google.common.base.Strings;
import com.google.common.primitives.Longs;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ShortUrlServiceImpl  implements ShortUrlService{
    private final ShortUrlRepository shortUrlRepository;

    public ShortUrlServiceImpl(ShortUrlRepository shortUrlRepository) {
        this.shortUrlRepository = shortUrlRepository;
    }

    @Override
    public List<ShortUrl> getAllShortUrls() {
        return shortUrlRepository.findAll();
    }

    @Override
    public String generateShortUrl(String longUrl) {
        ShortUrl foundShortUrl = shortUrlRepository.findByLongUrl(longUrl);
        if(foundShortUrl != null)
        {
            return foundShortUrl.getShortGeneratedUrl();
        }

        ShortUrl shortUrl = GenerateShortUrl(longUrl);
        shortUrlRepository.save(shortUrl);
        return shortUrl.getShortGeneratedUrl();
    }

    @Override
    public String getLongUrlByShortUrl(String shortUrl) {
        if(Strings.isNullOrEmpty(shortUrl))
        {
            return "";
        }
        if(shortUrl.length() == SHORT_URL_SIZE)
        {
            Long parsedUrl = GetIdFromShortUrl(shortUrl);
            Optional<ShortUrl> returnedRecord = shortUrlRepository.findById(parsedUrl);
            if(returnedRecord.isPresent())
            {
                String longUrl = returnedRecord.get().getLongUrl();
                return longUrl;
            }
        }
        return "";
    }

    final int SHORT_URL_SIZE = 8;
    private ShortUrl GenerateShortUrl(String LongUrl)
    {
        char[] generatedUrl = new char[SHORT_URL_SIZE];
        byte[] bytesArr = new byte[SHORT_URL_SIZE];
        for(int i=0; i<SHORT_URL_SIZE; i++) {
            int rand = getRandomNumber(0,62); //[0,62)
            bytesArr[i] = (byte) rand;
            generatedUrl[i] = GenerateRandomChar(rand);
        }
        Long id = Longs.fromByteArray(bytesArr);
        String strShortUrl = String.valueOf(generatedUrl);
        ShortUrl shortUrlObj = new ShortUrl();;
        shortUrlObj.setId(id);
        shortUrlObj.setLongUrl(LongUrl);
        shortUrlObj.setShortGeneratedUrl(strShortUrl);
        shortUrlObj.setExpireDate(LocalDateTime.now().plusDays(90));
        return shortUrlObj;
    }

    private Long GetIdFromShortUrl(String shortUrl)
    {
        char[] generatedUrl = shortUrl.toCharArray();
        byte[] bytesArr = new byte[SHORT_URL_SIZE];
        for(int i=0; i<SHORT_URL_SIZE; i++) {
            char c = generatedUrl[i];
            if((int)c >=(int)'A' && (int)c <= (int)'Z')
            {
                bytesArr[i] = (byte)((int)c - (int)'A');
            }
            else if((int)c >=(int)'a' && (int)c <= (int)'z')
            {
                bytesArr[i] = (byte)((int)c - (int)'a' + 26);
            }
            else
            {
                bytesArr[i] = (byte)((int)c - (int)'0' + 52);
            }
        }
        Long id = Longs.fromByteArray(bytesArr);
        return id;
    }

    private char GenerateRandomChar(int rand) {

        char resChar;
        if(rand < 26) //0-25
        {
            resChar =  (char)('A' + rand);
        }
        else if(rand < 52) // 26-51
        {
            resChar =  (char)('a' + rand - 26);
        }
        else // 52-61
        {
            resChar =  (char)('0' + rand - 52);
        }
        return resChar;
    }

    public int getRandomNumber(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }

}
