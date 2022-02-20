package com.spaishko.stringcloudstreamkafka.consumer.message;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "data_entity")
public class DataEntity {

    @Id
    @GeneratedValue
    @Column(name = "uuid")
    private UUID uuid;

    @NotEmpty
    @Column(name = "value")
    private String value;

    public DataEntity(String value) {
        this.value = value;
    }
}
