package io.takima.springdatabbl.config;

import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableCaching
public class CachingConfig {

    // Custom cache manager configuration (useful for EhCache)

//    @Bean
//    public CacheManager cacheManager() {
//        CacheManager cacheManager = CacheManagerBuilder.newCacheManagerBuilder()
//                .withCache("products",
//                        CacheConfigurationBuilder.newCacheConfigurationBuilder(
//                                        Long.class, Object.class,
//                                        ResourcePoolsBuilder.heap(1000)) // Maximum number of entries in cache
//                                .withExpiry(
//                                        Duration.of(10, TimeUnit.MINUTES) // Time-to-live for cache entries
//                                )
//                )
//                .build(true);
//        return cacheManager;
//    }
}
