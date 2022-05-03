package com.benjaminpalko.microservices.shared;

import org.springframework.data.annotation.Id;

import java.util.Date;
import java.util.UUID;

public class User extends MongoAuditing {
    @Id
    private final UUID id;
    protected User(Date createdOn, Date lastModifiedOn, String createBy, String lastModifiedBy, UUID id) {
        super(createdOn, lastModifiedOn, createBy, lastModifiedBy);
        this.id = id;
    }

    public UUID getId() {
        return id;
    }
}
