package ru.krasilova.otus.microservices.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
public enum StockAdapterAnswer {
    REGISTERED("REGISTERED"),
    NOT_REGISTERED("NOT_REGISTERED");

    @Getter
    private final String value;
}
