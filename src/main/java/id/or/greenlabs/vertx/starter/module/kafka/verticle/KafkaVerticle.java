package id.or.greenlabs.vertx.starter.module.kafka.verticle;

import com.google.inject.Guice;
import id.or.greenlabs.vertx.starter.ApplicationVerticle;
import id.or.greenlabs.vertx.starter.common.Environment;
import id.or.greenlabs.vertx.starter.module.VertxModule;
import id.or.greenlabs.vertx.starter.module.category.service.CategoryService;
import id.or.greenlabs.vertx.starter.module.kafka.KafkaModule;
import id.or.greenlabs.vertx.starter.module.kafka.config.KafkaConsumerConfig;
import id.or.greenlabs.vertx.starter.module.kafka.config.KafkaProducerConfig;

/**
 * @author krissadewo
 * @date 2/10/22 11:04 AM
 */
public class KafkaVerticle extends ApplicationVerticle<CategoryService> {

    private final Environment env;

    public KafkaVerticle(Environment env) {
        this.env = env;
    }

    @Override
    protected void buildModule() {
        KafkaProducerConfig producerConfig = KafkaProducerConfig.builder()
            .vertx(vertx)
            .env(env)
            .build();

        KafkaConsumerConfig consumerConfig = KafkaConsumerConfig.builder()
            .vertx(vertx)
            .env(env)
            .build();

        Guice.createInjector(
            new VertxModule(vertx),
            new KafkaModule(producerConfig, consumerConfig)
        );
    }
}
