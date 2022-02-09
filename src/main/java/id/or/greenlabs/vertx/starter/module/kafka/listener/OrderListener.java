package id.or.greenlabs.vertx.starter.module.kafka.listener;

import com.fasterxml.jackson.core.type.TypeReference;
import id.or.greenlabs.vertx.starter.assembler.dto.OrderDto;
import id.or.greenlabs.vertx.starter.module.kafka.config.KafkaConsumerConfig;
import id.or.greenlabs.vertx.starter.module.kafka.port.StockAdapter;
import id.or.greenlabs.vertx.starter.service.ApplicationService;
import id.or.greenlabs.vertx.starter.util.JsonUtil;
import io.vertx.core.Vertx;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.scheduler.Schedulers;
import reactor.kafka.receiver.KafkaReceiver;
import reactor.kafka.receiver.ReceiverOptions;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.Collections;
import java.util.List;

/**
 * @author krissadewo
 * @date 2/4/22 1:06 PM
 */
public class OrderListener extends ApplicationService {

    private final Logger logger = LoggerFactory.getLogger(OrderListener.class);

    private final ReceiverOptions<String, Object> receiverOptions;

    @Inject
    public StockAdapter adapter;

    @Inject
    public OrderListener(@Named("vertx") Vertx vertx) {
        super(vertx);

        receiverOptions = context.get(KafkaConsumerConfig.class).getReceiverOptions();

        listenOrder();
    }

    private void listenOrder() {
        KafkaReceiver.create(receiverOptions())
            .receive()
            .publishOn(Schedulers.boundedElastic())
            .doOnNext(record -> {
                List<OrderDto> dtos = JsonUtil.fromJson((String) record.value(), new TypeReference<>() {
                });

                adapter.save(dtos).subscribe();
            })
            .subscribe();
    }

    private ReceiverOptions<String, Object> receiverOptions() {
        String topic = "order";

        return receiverOptions.subscription(Collections.singleton(topic))
            .addAssignListener(receiverPartitions -> {
                receiverPartitions.forEach(receiverPartition -> {
                    receiverPartition.seek(receiverPartition.position());
                });
            });
    }
}
