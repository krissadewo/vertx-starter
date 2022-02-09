package id.or.greenlabs.vertx.starter.module.kafka;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.name.Named;
import id.or.greenlabs.vertx.starter.module.kafka.config.KafkaConsumerConfig;
import id.or.greenlabs.vertx.starter.module.kafka.config.KafkaProducerConfig;
import id.or.greenlabs.vertx.starter.module.kafka.listener.KafkaEvbListener;
import id.or.greenlabs.vertx.starter.module.kafka.listener.OrderListener;
import id.or.greenlabs.vertx.starter.module.kafka.port.KafkaAdapter;
import id.or.greenlabs.vertx.starter.module.kafka.port.StockAdapter;
import id.or.greenlabs.vertx.starter.module.kafka.service.KafkaService;
import id.or.greenlabs.vertx.starter.module.kafka.service.StockService;
import id.or.greenlabs.vertx.starter.module.kafka.usecase.SendOrder;
import id.or.greenlabs.vertx.starter.module.kafka.usecase.impl.SendOrderImpl;

/**
 * @author krissadewo
 * @date 2/4/22 10:17 AM
 */
public class KafkaModule extends AbstractModule {

    private final KafkaConsumerConfig kafkaConsumerConfig;

    private final KafkaProducerConfig kafkaProducerConfig;

    public KafkaModule(KafkaProducerConfig kafkaProducerConfig, KafkaConsumerConfig kafkaConsumerConfig) {
        this.kafkaProducerConfig = kafkaProducerConfig;
        this.kafkaConsumerConfig = kafkaConsumerConfig;
    }

    @Provides
    @Named("kafkaProducerConfig")
    KafkaProducerConfig kafkaProducerConfig() {
        return this.kafkaProducerConfig;
    }

    @Provides
    @Named("kafkaConsumerConfig")
    KafkaConsumerConfig kafkaConsumerConfig() {
        return this.kafkaConsumerConfig;
    }

    @Override
    protected void configure() {
        bind(KafkaEvbListener.class).asEagerSingleton();
        bind(KafkaAdapter.class).to(KafkaService.class);
        bind(SendOrder.class).to(SendOrderImpl.class);
        bind(StockAdapter.class).to(StockService.class);
        bind(OrderListener.class).asEagerSingleton();
    }
}
