package com.salex.actuatordemo.actuator;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.simple.SimpleMeterRegistry;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {

    @Bean
    public SimpleMeterRegistry registry() {
        return new SimpleMeterRegistry();
    }

    @Bean
    public Counter counter() {
        return registry()
                .counter("app.counter.foo");
    }
}
