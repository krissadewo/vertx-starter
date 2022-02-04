package id.or.greenlabs.vertx.starter.module.order.port;

import id.or.greenlabs.vertx.starter.assembler.dto.OrderDto;
import reactor.core.publisher.Mono;

import java.util.List;

/**
 * @author krissadewo
 * @date 2/4/22 10:34 AM
 */
public interface KafkaAdapter {

    Mono<Object> send(List<OrderDto> dtos);
}
