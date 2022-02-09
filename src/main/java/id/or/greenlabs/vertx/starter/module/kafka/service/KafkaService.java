package id.or.greenlabs.vertx.starter.module.kafka.service;

import com.google.inject.name.Named;
import id.or.greenlabs.vertx.starter.assembler.dto.OrderDto;
import id.or.greenlabs.vertx.starter.common.StatusCode;
import id.or.greenlabs.vertx.starter.module.kafka.config.KafkaProducerConfig;
import id.or.greenlabs.vertx.starter.module.kafka.port.KafkaAdapter;
import id.or.greenlabs.vertx.starter.service.ApplicationService;
import io.vertx.core.Vertx;
import io.vertx.core.impl.logging.Logger;
import io.vertx.core.impl.logging.LoggerFactory;
import io.vertx.core.json.Json;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import reactor.core.publisher.Mono;
import reactor.kafka.sender.KafkaSender;
import reactor.kafka.sender.SenderRecord;

import javax.inject.Inject;
import java.util.List;

/**
 * @author krissadewo
 * @date 2/4/22 10:11 AM
 */
public class KafkaService extends ApplicationService implements KafkaAdapter {

    private final Logger logger = LoggerFactory.getLogger(KafkaService.class);

    private KafkaSender<String, Object> producer;

    @Inject
    public KafkaService(@Named("vertx") Vertx vertx, @Named("kafkaProducerConfig") KafkaProducerConfig kafkaProducerConfig) {
        super(vertx);

        this.producer = kafkaProducerConfig.getProducer();
    }

    @Override
    public Mono<String> send(List<OrderDto> dtos) {
        String topic = "order";

        ProducerRecord<String, Object> record = new ProducerRecord<>(topic, Json.encodePrettily(dtos));

        return producer
            .send(Mono.just(SenderRecord.create(record, null)))
            .singleOrEmpty()
            .flatMap(result -> {
                RecordMetadata metadata = result.recordMetadata();

                dtos.forEach(dto -> {
                    logger.info("Message with id " + dto.getId() + " written on topic=" + metadata.topic() +
                        ", partition=" + metadata.partition() +
                        ", offset=" + metadata.offset());
                });

                return Mono.just(StatusCode.OPERATION_SUCCESS);
            })
            .onErrorReturn(StatusCode.OPERATION_FAILED);
    }
}
