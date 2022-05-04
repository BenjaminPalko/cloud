package com.benjaminpalko.microservices.shared.users;

import java.io.Serializable;
import java.util.Date;

public record User(String firstName, String lastName, Date birthday) implements Serializable {
}
