package id.or.greenlabs.vertx.starter.module.kafka.port;

import id.or.greenlabs.vertx.starter.assembler.dto.OrderDto;
import reactor.core.publisher.Mono;

import java.util.List;

/**
 * @author krissadewo
 * @date 2/4/22 1:13 PM
 */
public interface StockAdapter {

    Mono<Object> save(List<OrderDto> dtos);

}
