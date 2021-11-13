package ru.krasilova.otus.microservices.kafka.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import ru.krasilova.otus.microservices.model.Contract;
import ru.krasilova.otus.microservices.model.MethodType;


@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class ContractEventDto {

    @JsonProperty("profile_id")
    private Long profileId;

    @JsonProperty("contract")
    private Contract contract;

    @JsonProperty("method_type")
    private MethodType methodType;
}
