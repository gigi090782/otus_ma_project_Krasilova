package ru.krasilova.otus.microservices.kafka.consumer;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ProfileTopic {
    CREATE_PROFILE("profile_created"),
    DELETE_PROFILE("profile_deleted");

    private String value;
}
