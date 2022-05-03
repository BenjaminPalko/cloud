package com.benjaminpalko.microservices.shared.users;

import com.benjaminpalko.microservices.shared.mongo.MongoAuditing;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceConstructor;

import java.util.Date;
import java.util.UUID;

public class UserDocument extends MongoAuditing {
    @Id
    private final UUID id;
    private final String firstName;
    private final String lastName;
    private final Date birthday;

    public static UserDocument FromUser(User user) {
        return new UserDocument(user.firstName(), user.lastName(), user.birthday());
    }

    public UserDocument(String firstName, String lastName, Date birthday) {
        this(new Date(), new Date(), "SYSTEM", "SYSTEM", UUID.randomUUID(), firstName, lastName, birthday);
    }

    @PersistenceConstructor
    protected UserDocument(Date createdOn, Date lastModifiedOn, String createBy, String lastModifiedBy, UUID id, String firstName, String lastName, Date birthday) {
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
