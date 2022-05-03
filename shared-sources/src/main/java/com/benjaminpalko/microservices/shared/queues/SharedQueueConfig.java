package com.benjaminpalko.microservices.shared.queues;

import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SharedQueueConfig {

    @Bean("userQueueName")
    public String userQueueName() {
        return "userQueue";
    }

    @Bean("userQueue")
    public Queue userQueue(@Qualifier("userQueueName") String userQueueName) {
        return new Queue(userQueueName, false);
    }
}
