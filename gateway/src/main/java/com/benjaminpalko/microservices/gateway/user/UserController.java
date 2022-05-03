package com.benjaminpalko.microservices.gateway.user;

import com.benjaminpalko.microservices.shared.users.User;
import com.benjaminpalko.microservices.shared.users.UserDocument;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(path = "/api")
public class UserController {

    private static final Logger logger = LogManager.getLogger(UserController.class);
    private final RabbitTemplate rabbitTemplate;

    @Autowired
    public UserController(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @RequestMapping(path = "/user", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<UserResponse>> createUser(@RequestBody Mono<UserRequest> request, @Qualifier("userQueueName") String userQueueName) {
        return request
                .log()
                .mapNotNull(req -> rabbitTemplate.convertSendAndReceive(userQueueName, req))
                .flatMap(o -> {
                    if(o instanceof User user) {
                        return Mono.just(UserResponse.FromUser(user));
                    }
                    return Mono.error(new InstantiationException("User Service response not of type User"));
                })
                .map(ResponseEntity::ok)
                .onErrorResume(throwable -> {
                    logger.error("Error with User Service response", throwable);
                    return Mono.just(ResponseEntity.internalServerError().build());
                });
    }
}
