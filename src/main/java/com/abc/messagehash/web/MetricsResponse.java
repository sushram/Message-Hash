package com.abc.messagehash.web;

import java.util.Map;

public class MetricsResponse {
    private String message;
    private Map<String,Long> metrics;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Map<String, Long> getMetrics() {
        return metrics;
    }

    public void setMetrics(Map<String, Long> metrics) {
        this.metrics = metrics;
    }
}
