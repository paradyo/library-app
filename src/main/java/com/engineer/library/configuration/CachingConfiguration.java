package com.engineer.library.configuration;

import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableCaching
public class CachingConfiguration {

    @Bean
    public ConcurrentMapCacheManager cacheManager(){
        //return new ConcurrentMapCacheManager("books", "authors");
        return new ConcurrentMapCacheManager("books");
    }
}
