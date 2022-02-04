package id.or.greenlabs.vertx.starter.module.order.usecase;

import id.or.greenlabs.vertx.starter.assembler.dto.OrderDto;
import reactor.core.publisher.Mono;

import java.util.List;

/**
 * @author krissadewo
 * @date 2/3/22 5:41 PM
 */
public interface OrderProduct {

    Mono<Object> execute(List<OrderDto> dtos);
}
