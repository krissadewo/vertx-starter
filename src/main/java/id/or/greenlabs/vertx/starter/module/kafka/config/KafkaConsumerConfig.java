package id.or.greenlabs.vertx.starter.module.kafka.config;

import id.or.greenlabs.vertx.starter.common.Environment;
import id.or.greenlabs.vertx.starter.context.ShareableContext;
import io.vertx.core.Vertx;
import lombok.Builder;
import lombok.Getter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.kafka.receiver.ReceiverOptions;

import java.util.HashMap;
import java.util.Map;

/**
 * @author krissadewo
 * @date 2/4/22 1:04 PM
 */
@Getter
public class KafkaConsumerConfig extends ShareableContext<KafkaConsumerConfig> {

    private final Logger logger = LoggerFactory.getLogger(KafkaConsumerConfig.class);

    private final Environment env;

    @Builder
    private KafkaConsumerConfig(Vertx vertx, Environment env) {
        super(vertx);

        this.env = env;
    }

    public ReceiverOptions<String, Object> getReceiverOptions() {
        Map<String, Object> properties = new HashMap<>();
        properties.put("bootstrap.servers", env.getKafka().get("servers"));
        properties.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        properties.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        properties.put("group.id", "group.order");
        properties.put("enable.auto.commit", "false");

        return ReceiverOptions.create(properties);
    }

    @Override
    protected KafkaConsumerConfig registerConfig() {
        return this;
    }
}
