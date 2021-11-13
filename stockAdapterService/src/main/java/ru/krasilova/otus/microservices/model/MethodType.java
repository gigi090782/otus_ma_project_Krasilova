package ru.krasilova.otus.microservices.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum MethodType {
    ADD,
    EDIT,
    DELETE,
    DELETED_ALL
}
