package id.or.greenlabs.vertx.starter.module.order.usecase.impl;

import id.or.greenlabs.vertx.starter.assembler.dto.OrderDto;
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
    private OrderAdapter adapter;

    @Override
    public Mono<Integer> execute(List<OrderDto> dtos) {
        return adapter.save(dtos);
    }
}
