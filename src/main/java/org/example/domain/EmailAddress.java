package org.example.domain;

import java.util.Objects;
import java.util.regex.Pattern;

public record EmailAddress(String email) {

    private static final Pattern EMAIL_PATTERN = Pattern.compile(
            "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$"
    );

    public EmailAddress{
        Objects.requireNonNull(email, "Email address can not be null");

        if(!EMAIL_PATTERN.matcher(email).matches()){
            throw new IllegalArgumentException("Invalid email address format: " + email);
        }
    }
}
