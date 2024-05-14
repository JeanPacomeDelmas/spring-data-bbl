package io.takima.springdatabbl.utils;

import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.concurrent.ConcurrentMapCache;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentMap;
import java.util.stream.Collectors;

@Component
public class CachePrinter {

    private final CacheManager cacheManager;

    public CachePrinter(CacheManager cacheManager) {
        this.cacheManager = cacheManager;
    }

    public List<String> printCacheContentLineByLine(String cacheName) {
        Cache barmanCocktailsCache = cacheManager.getCache(cacheName);
        if (barmanCocktailsCache == null) {
            throw new NullPointerException("Cache not found for name " + cacheName);
        }

        ConcurrentMapCache concurrentMapCache = (ConcurrentMapCache) barmanCocktailsCache;
        ConcurrentMap<Object, Object> cacheMap = concurrentMapCache.getNativeCache();

        return CacheMapFormatter.formatEntries(cacheMap);
    }

    private static class CacheMapFormatter {
        public static List<String> formatEntries(Map<Object, Object> cacheMap) {
            return cacheMap.entrySet()
                    .stream()
                    .map(entry -> "Key: " + entry.getKey() + ", Value: " + entry.getValue())
                    .collect(Collectors.toList());
        }
    }
}
