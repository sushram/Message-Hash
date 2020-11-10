package com.abc.messagehash.web;

import com.abc.messagehash.buisness.service.RequestCounter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/metrics")
public class MetricsController {


    private final RequestCounter counter;

    @Autowired
    public MetricsController(RequestCounter counter) {
        this.counter = counter;
    }

    @GetMapping
    public ResponseEntity<MetricsResponse> getMetrics() {
        Map<String, Long> metrics = counter.dumpCounters();
        MetricsResponse resp = new MetricsResponse();
        resp.setMessage(String.format("Number of request hits per %d hour", TimeUnit.MILLISECONDS.toHours(RequestCounter.DELAY)));
        resp.setMetrics(metrics);

        return new ResponseEntity<>(resp, HttpStatus.OK);
    }

}
