package com.spaishko.stringcloudstreamkafka.consumer.message;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "message")
public class Message {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(name = "uuid", unique = true, columnDefinition = "BINARY(16)")
    private UUID uuid;

    @NotEmpty
    @Column(name = "value")
    private String value;

    public Message(String value) {
        this.value = value;
    }
}
