package com.benjaminpalko.microservices.userservice;

import com.benjaminpalko.microservices.shared.users.User;
import com.benjaminpalko.microservices.shared.users.UserDocument;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class UserDocumentService {

    private final UserDocumentRepository userDocumentRepository;

    public UserDocumentService(UserDocumentRepository userDocumentRepository) {
        this.userDocumentRepository = userDocumentRepository;
    }

    @RabbitListener(queues = "userQueue")
    public User listenUserQueue(User user) {
        return userDocumentRepository.save(UserDocument.FromUser(user))
                .map(User::FromUserDocument)
                .block();
    }
}
