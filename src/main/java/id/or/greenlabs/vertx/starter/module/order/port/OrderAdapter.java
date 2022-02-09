package id.or.greenlabs.vertx.starter.module.order.port;

import id.or.greenlabs.vertx.starter.assembler.dto.OrderDto;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

/**
 * @author krissadewo
 * @date 2/3/22 5:32 PM
 */
public interface OrderAdapter {

    Mono<List<OrderDto>> save(List<OrderDto> dtos);

    Flux<OrderDto> find(OrderDto param, int limit, int offset);
}
