package ru.krasilova.otus.microservices.database.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "contract")
public class Contract {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private Long profileId;


    @Column
    private String number;

    @Column
    @Builder.Default
    private boolean marketPlaceFx = true;

    @Column
    private boolean marketPlaceForts;

    @Column
    private boolean marketPlaceFond;

    @Builder.Default
    @Version
    private Long version = 0L;
}
