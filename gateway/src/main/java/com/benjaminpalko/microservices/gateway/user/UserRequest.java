package com.benjaminpalko.microservices.gateway.user;

import com.benjaminpalko.microservices.shared.users.User;

import java.util.Date;

public record UserRequest(String firstName, String lastName) {
    public User toUser() {
        return new User(firstName, lastName, new Date());
    }
}
