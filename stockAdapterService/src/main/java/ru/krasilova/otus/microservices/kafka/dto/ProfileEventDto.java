package ru.krasilova.otus.microservices.kafka.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import ru.krasilova.otus.microservices.model.MethodType;
import ru.krasilova.otus.microservices.model.Profile;


@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class ProfileEventDto {

    @JsonProperty("profile")
    private Profile profile;

    @JsonProperty("contract")
    private ContractEventDto contractEventDto;

    @JsonProperty("method_type")
    private MethodType methodType;
}
