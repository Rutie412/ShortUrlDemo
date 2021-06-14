package com.example.demoShortUrl.domain;

//import lombok.Data;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Data
@Entity
public class ShortUrl {

    @Id //@GeneratedValue(strategy = GenerationType.IDENTITY) - NOT generated.
    private Long id;
    private String longUrl;
    private String shortGeneratedUrl;
    private LocalDateTime expireDate;

}
