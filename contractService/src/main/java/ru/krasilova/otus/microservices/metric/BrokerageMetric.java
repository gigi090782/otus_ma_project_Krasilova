package ru.krasilova.otus.microservices.metric;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum BrokerageMetric {
    PROFILE_EVENT_CONSUMED ("profile_event_consumed_total"),
    CONTRACT_PRODUCED("contract_produced_total");

    @Getter
    private final String metricName;
}
