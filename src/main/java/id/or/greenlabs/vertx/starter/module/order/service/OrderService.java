package id.or.greenlabs.vertx.starter.module.order.service;

import id.or.greenlabs.vertx.starter.assembler.dto.OrderDto;
import id.or.greenlabs.vertx.starter.assembler.wrapper.OrderWrapper;
import id.or.greenlabs.vertx.starter.module.order.port.OrderAdapter;
import id.or.greenlabs.vertx.starter.module.order.repository.OrderRepository;
import id.or.greenlabs.vertx.starter.service.ApplicationService;
import io.vertx.core.Vertx;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.ArrayList;
import java.util.List;

/**
 * @author krissadewo
 * @date 2/3/22 7:23 PM
 */
public class OrderService extends ApplicationService implements OrderAdapter {

    private final OrderRepository repository;

    @Inject
    public OrderService(@Named("vertx") Vertx vertx, OrderRepository repository) {
        super(vertx);

        this.repository = repository;
    }

    @Override
    public Mono<List<OrderDto>> save(List<OrderDto> dtos) {
        return repository.save(new ArrayList<>(new OrderWrapper().toDocument(dtos)))
            .map(result -> {
                return new ArrayList<>(new OrderWrapper().toDto(result));
            });
    }

    @Override
    public Flux<OrderDto> findBy(OrderDto param, int limit, int offset) {
        return repository.findBy(new OrderWrapper().toParam(param), limit, offset)
            .map(result -> {
                return new OrderWrapper().toDto(result);
            });
    }
}
