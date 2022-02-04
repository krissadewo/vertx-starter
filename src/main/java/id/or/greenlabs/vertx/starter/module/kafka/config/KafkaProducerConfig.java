package id.or.greenlabs.vertx.starter.module.kafka.config;

import id.or.greenlabs.vertx.starter.common.Environment;
import id.or.greenlabs.vertx.starter.context.ShareableContext;
import io.vertx.core.Vertx;
import lombok.Builder;
import lombok.Getter;
import reactor.kafka.sender.KafkaSender;
import reactor.kafka.sender.SenderOptions;

import java.util.HashMap;
import java.util.Map;

/**
 * @author krissadewo
 * @date 2/4/22 10:07 AM
 */
@Getter
public class KafkaProducerConfig extends ShareableContext<KafkaProducerConfig> {

    private final KafkaSender<String, Object> producer;

    /**
     * @param vertx
     */
    @Builder
    public KafkaProducerConfig(Vertx vertx, Environment env) {
        super(vertx);

        Map<String, Object> properties = new HashMap<>();
        properties.put("bootstrap.servers", env.getKafka().get("servers"));
        properties.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        properties.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        properties.put("acks", "all");
        properties.put("enable.idempotence", "true");
        properties.put("compression.type", "lz4");
        properties.put("sasl.mechanism", "PLAIN");

        producer = KafkaSender.create(SenderOptions.create(properties));
    }

    @Override
    protected KafkaProducerConfig registerConfig() {
        return this;
    }
}
