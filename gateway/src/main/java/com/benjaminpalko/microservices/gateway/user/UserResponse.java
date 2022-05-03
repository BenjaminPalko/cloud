package com.benjaminpalko.microservices.gateway.user;

import com.benjaminpalko.microservices.shared.users.User;

import java.util.Date;

public record UserResponse(String firstName, String lastName, Date birthday) {
    public static UserResponse FromUser(User user) {
        return new UserResponse(user.firstName(), user.lastName(), user.birthday());
    }
}
