package com.benjaminpalko.microservices.shared;

import org.springframework.data.annotation.Id;

import java.util.Date;
import java.util.UUID;

public class User extends MongoAuditing {
    @Id
    private final UUID id;
    private final String firstName;
    private final String lastName;
    private final Date birthday;
    protected User(Date createdOn, Date lastModifiedOn, String createBy, String lastModifiedBy, UUID id, String firstName, String lastName, Date birthday) {
        super(createdOn, lastModifiedOn, createBy, lastModifiedBy);
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthday = birthday;
    }

    public UUID getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Date getBirthday() {
        return birthday;
    }
}
