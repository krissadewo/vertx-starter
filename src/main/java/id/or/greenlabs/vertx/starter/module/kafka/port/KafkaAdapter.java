package id.or.greenlabs.vertx.starter.module.kafka.port;

import id.or.greenlabs.vertx.starter.assembler.dto.OrderDto;
import reactor.core.publisher.Mono;

import java.util.List;

/**
 * @author krissadewo
 * @date 2/4/22 10:10 AM
 */
public interface KafkaAdapter {

    Mono<String> send(List<OrderDto> dtos);
}
