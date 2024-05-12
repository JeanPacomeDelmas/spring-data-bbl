package io.takima.springdatabbl.service.customCaching;

import io.takima.springdatabbl.model.Barman;
import org.springframework.cache.support.SimpleValueWrapper;

import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class CustomCache implements org.springframework.cache.Cache {

    private final String name;
    private final ConcurrentMap<Object, Object> cache = new ConcurrentHashMap<>();

    public CustomCache(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Object getNativeCache() {
        return cache;
    }

    @Override
    public ValueWrapper get(Object key) {
        Object value = cache.get(key);
        return (value != null) ? new SimpleValueWrapper(value) : null;
    }

    @Override
    public <T> T get(Object key, Class<T> type) {
        Object value = cache.get(key);
        return (value != null) ? (T) new SimpleValueWrapper(value) : null;
    }

    @Override
    public <T> T get(Object key, Callable<T> valueLoader) {
        return null;
    }

    @Override
    public void put(Object key, Object value) {
        // Custom caching strategy: Only cache Barman objects if their name length is less than 5
        if (value instanceof Barman) {
            Barman barman = (Barman) value;
            if (barman.getName().length() < 5) {
                cache.put(key, value);
            }
        } else {
            cache.put(key, value);
        }
    }

    @Override
    public void evict(Object key) {
        cache.remove(key);
    }

    @Override
    public void clear() {
        cache.clear();
    }
}
