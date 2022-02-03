package id.or.greenlabs.vertx.starter.module.order.repository;

import id.or.greenlabs.vertx.starter.document.Order;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * @author krissadewo
 * @date 2/3/22 10:46 AM
 */
public interface OrderRepository {

    Mono<Order> save(Order document);

    Flux<Order> find(Order param, int limit, int offset);
}
