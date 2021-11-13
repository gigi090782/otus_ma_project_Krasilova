package ru.krasilova.otus.microservices.metric;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum BrokerageMetric {
    CONTRACT_EVENT_CONSUMED("contract_event_consumed_total"),
    PROFILE_EVENT_CONSUMED ("profile_event_consumed_total"),
    STOCK_ADAPTER_PRODUCED("stock_adapter_event_produced_total");

    @Getter
    private final String metricName;
}
