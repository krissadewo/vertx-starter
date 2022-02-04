package id.or.greenlabs.vertx.starter.module.kafka.usecase;

import id.or.greenlabs.vertx.starter.assembler.dto.OrderDto;
import reactor.core.publisher.Mono;

import java.util.List;

/**
 * @author krissadewo
 * @date 2/4/22 10:20 AM
 */
public interface SendOrder {

    Mono<String> execute(List<OrderDto> dtos);
}
