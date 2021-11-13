package ru.krasilova.otus.microservices.kafka.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class StockAdapterEventDto {
    @JsonProperty("profile_id")
    private Long profileId;

    @JsonProperty("profile_status")
    private String  profileStatus;
}

