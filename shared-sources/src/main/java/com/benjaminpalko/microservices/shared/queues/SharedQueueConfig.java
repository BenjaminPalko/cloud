package com.benjaminpalko.microservices.shared.queues;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SharedQueueConfig {

    @Bean
    public Queue userQueue() {
        return new Queue("userQueue", false);
    }
}
