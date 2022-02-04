package id.or.greenlabs.vertx.starter.module.kafka.usecase.impl;

import id.or.greenlabs.vertx.starter.assembler.dto.OrderDto;
import id.or.greenlabs.vertx.starter.module.kafka.port.KafkaAdapter;
import id.or.greenlabs.vertx.starter.module.kafka.usecase.SendOrder;
import reactor.core.publisher.Mono;

import javax.inject.Inject;
import java.util.List;

/**
 * @author krissadewo
 * @date 2/4/22 10:21 AM
 */
public class SendOrderImpl implements SendOrder {

    @Inject
    private KafkaAdapter adapter;

    @Override
    public Mono<String> execute(List<OrderDto> dtos) {
        return adapter.send(dtos);
    }
}
