package com.spaishko.stringcloudstreamkafka.consumer;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.channel.QueueChannel;
import org.springframework.integration.handler.advice.IdempotentReceiverInterceptor;
import org.springframework.integration.jdbc.metadata.JdbcMetadataStore;
import org.springframework.integration.selector.MetadataStoreSelector;
import org.springframework.integration.transaction.TransactionInterceptorBuilder;
import org.springframework.messaging.PollableChannel;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.interceptor.TransactionInterceptor;

import javax.sql.DataSource;
import java.util.Date;
import java.util.Objects;

@Configuration
@AllArgsConstructor
public class Configs {

    public static final String IDEMPOTENT_DISCARD_CHANNEL = "idempotentDiscardChannel";

    @Bean
    public JdbcMetadataStore metadataStore(DataSource dataSource) {
        return new JdbcMetadataStore(dataSource);
    }

    @Bean
    public IdempotentReceiverInterceptor idempotentReceiverInterceptor(JdbcMetadataStore metadataStore) {

        MetadataStoreSelector metadataStoreSelector =
                new MetadataStoreSelector(
                        m -> Objects.requireNonNull(m.getHeaders().get("x-message-id")).toString(),
                        m -> Long.toString(new Date().getTime()),
                        metadataStore);

        IdempotentReceiverInterceptor idempotentReceiverInterceptor = new IdempotentReceiverInterceptor(metadataStoreSelector);
        idempotentReceiverInterceptor.setDiscardChannelName(IDEMPOTENT_DISCARD_CHANNEL);
        idempotentReceiverInterceptor.setThrowExceptionOnRejection(false);
        return idempotentReceiverInterceptor;
    }

    @Bean
    public TransactionInterceptor intTransactionInterceptor(PlatformTransactionManager transactionManager) {
        return new TransactionInterceptorBuilder(true)
                .transactionManager(transactionManager)
                .isolation(Isolation.READ_COMMITTED)
                .build();
    }

    @Bean
    public PollableChannel idempotentDiscardChannel() {
        return new QueueChannel();
    }


}