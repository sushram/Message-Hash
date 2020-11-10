package com.abc.messagehash.buisness.service;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class RequestCounter {

    private final Map<String,AtomicLong> counters;
    private Long lastUpdated;
    public static final Long DELAY = TimeUnit.HOURS.toMillis(1);

    public RequestCounter() {
        this.counters = new ConcurrentHashMap<>();
        lastUpdated = System.currentTimeMillis();
    }

    public void increment(String key) {
        resetCountersIf();
        AtomicLong counter = counters.get(key);
        if(counter == null) {
            AtomicLong tmpValue = new AtomicLong(0l);
            counter = counters.putIfAbsent(key, tmpValue);
            counter = counter == null ? tmpValue : counter;
        }

        counter.getAndIncrement();

    }

    public Map<String,Long> dumpCounters() {
        resetCountersIf();
        Map<String,Long> result = new HashMap<>();

        Set<Map.Entry<String,AtomicLong>> keySet = counters.entrySet();
        for(Map.Entry<String,AtomicLong> entry : keySet) {
            result.put(entry.getKey(),entry.getValue().longValue());
        }

        return result;
    }

    private synchronized void resetCountersIf() {
        if(System.currentTimeMillis() > lastUpdated + DELAY) {
            clearCounters();
        }
    }

    private void clearCounters() {
        this.counters.clear();
        lastUpdated = System.currentTimeMillis();
    }

}
