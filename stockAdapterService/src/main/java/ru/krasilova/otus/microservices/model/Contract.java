package ru.krasilova.otus.microservices.model;

import java.util.Date;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class Contract {
    Long id;
    Long profileId;
    String number;
    Date createdDate;
    MethodType methodType;
    boolean marketPlaceFx;
    boolean marketPlaceForts;
    boolean marketPlaceFond;
    Long version;
}
