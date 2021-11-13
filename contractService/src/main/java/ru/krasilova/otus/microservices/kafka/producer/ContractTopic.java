package ru.krasilova.otus.microservices.kafka.producer;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ContractTopic {
    CREATE_CONTRACT("contract_created"),
    UPDATE_CONTRACT("contract_updated"),
    DELETE_CONTRACT("contract_deleted"),
    DELETE_ALL_CONTRACT("contract_deleted_all"),
    DELETE_ALL_CONTRACT_REJECTED("contract_deleted_all_rejected");

    private String value;
}
