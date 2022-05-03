package com.benjaminpalko.microservices.shared.users;

import java.util.Date;

public record User(String firstName, String lastName, Date birthday) {
    public static User FromUserDocument(UserDocument userDocument) {
        return new User(userDocument.getFirstName(), userDocument.getLastName(), userDocument.getBirthday());
    }
}
