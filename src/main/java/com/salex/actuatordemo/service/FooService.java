package com.salex.actuatordemo.service;

import io.micrometer.core.instrument.Counter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FooService {

    private long cnt = 0;
    private final Counter counter;

    public Long increment() {
        counter.increment(1.0);
        return ++cnt;
    }
}
