package id.or.greenlabs.vertx.starter.module.order.usecase;

import id.or.greenlabs.vertx.starter.assembler.dto.OrderDto;
import reactor.core.publisher.Flux;

/**
 * @author krissadewo
 * @date 2/3/22 7:36 PM
 */
public interface Find {

    Flux<OrderDto> execute(OrderDto dto, int limit, int offset);

}
