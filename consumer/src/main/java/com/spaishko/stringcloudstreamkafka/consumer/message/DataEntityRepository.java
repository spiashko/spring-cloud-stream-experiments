package com.spaishko.stringcloudstreamkafka.consumer.message;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface DataEntityRepository extends JpaRepository<DataEntity, UUID> {

}
