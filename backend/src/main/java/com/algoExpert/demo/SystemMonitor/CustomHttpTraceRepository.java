package com.algoExpert.demo.SystemMonitor;


import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;


    @Component
    public class CustomHttpTraceRepository {
        private final Counter featureUsageCounter;
        @Autowired
        public CustomHttpTraceRepository(MeterRegistry registry) {
            featureUsageCounter = registry.counter("feature.usage", "feature", "myFeatures");
        }
        private final ConcurrentHashMap<String, AtomicLong> requestCountMap = new ConcurrentHashMap<>();

        public void incrementRequestCount(String featureType) {
            requestCountMap.computeIfAbsent(featureType, k -> new AtomicLong()).incrementAndGet();
        }
        public long getRequestCount(String uri) {
            return requestCountMap.getOrDefault(uri, new AtomicLong(0)).get();
        }





        public void incrementFeatureUsage() {
            featureUsageCounter.increment();
        }



}

