package ru.krasilova.otus.microservices.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum ProfileStatus {
    ON_REGISTRATION("ON_REGISTRATION"),
    REGISTERED("REGISTERED"),
    REGISTRATION_DELETED("REGISTRATION_DELETED"),
    NOT_REGISTERED("NOT_REGISTERED");

    @Getter
    private final String value;
}
