package ru.krasilova.otus.microservices.database.entity;

import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
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
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@TypeDefs(value = {
        @TypeDef(name = "jsonb", typeClass = JsonBinaryType.class)
})
@Table(name = "message")
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private Long profileId;

    @Type(type = "jsonb")
    @Column(columnDefinition = "jsonb")
    private String message;

    @Builder.Default
    @Version
    private Long version = 0L;

}