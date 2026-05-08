package org.example.domain;

import java.util.Objects;

public record CustomerName(String name) {
    public CustomerName{
        Objects.requireNonNull(name);
    }
}
