package com.benjaminpalko.microservices.userservice;

import com.benjaminpalko.microservices.shared.users.UserDocument;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import java.util.UUID;

public interface UserDocumentRepository extends ReactiveMongoRepository<UserDocument, UUID> {
}
