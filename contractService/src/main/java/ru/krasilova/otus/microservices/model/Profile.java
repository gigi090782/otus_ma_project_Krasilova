package ru.krasilova.otus.microservices.model;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Version;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class Profile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private Long userId;

    @Column
    private String firstName;

    @Column
    private String middleName;

    @Column
    private String lastName;

    @Builder.Default
    private String status = ProfileStatus.ON_REGISTRATION.getValue();

    @Column
    private String countryCode;

    @Column
    private Long documentType;

    @Column
    private String documentSeries;

    @Column
    private String documentNumber;

    @Builder.Default
    @Version
    private Long version = 0L;

}