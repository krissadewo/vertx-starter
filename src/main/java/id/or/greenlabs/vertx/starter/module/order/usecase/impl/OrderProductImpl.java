package id.or.greenlabs.vertx.starter.module.order.usecase.impl;

import id.or.greenlabs.vertx.starter.assembler.dto.OrderDto;
import id.or.greenlabs.vertx.starter.module.order.port.KafkaAdapter;
import id.or.greenlabs.vertx.starter.module.order.port.OrderAdapter;
import id.or.greenlabs.vertx.starter.module.order.usecase.OrderProduct;
import reactor.core.publisher.Mono;

import javax.inject.Inject;
import java.util.List;

/**
 * @author krissadewo
 * @date 2/3/22 5:43 PM
 */
public class OrderProductImpl implements OrderProduct {

    @Inject
    private OrderAdapter orderAdapter;

    @Inject
    private KafkaAdapter kafkaAdapter;

    @Override
    public Mono<Object> execute(List<OrderDto> dtos) {
        return orderAdapter.save(dtos)
            .flatMap(integer -> {
                if (integer > 0) {
                    return kafkaAdapter.send(dtos);
                }

                return Mono.empty();
            });
    }
}
