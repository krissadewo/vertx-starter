package id.or.greenlabs.vertx.starter.module.order.usecase.impl;

import id.or.greenlabs.vertx.starter.assembler.dto.OrderDto;
import id.or.greenlabs.vertx.starter.module.order.port.OrderAdapter;
import id.or.greenlabs.vertx.starter.module.order.usecase.Find;
import reactor.core.publisher.Flux;

import javax.inject.Inject;

/**
 * @author krissadewo
 * @date 2/3/22 7:37 PM
 */
public class FindImpl implements Find {

    @Inject
    private OrderAdapter adapter;

    @Override
    public Flux<OrderDto> execute(OrderDto dto, int limit, int offset) {
        return adapter.find(dto, limit, offset);
    }
}
