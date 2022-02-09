package id.or.greenlabs.vertx.starter.module.stock.usecase;

import id.or.greenlabs.vertx.starter.assembler.dto.OrderDto;
import id.or.greenlabs.vertx.starter.assembler.dto.StockDto;
import reactor.core.publisher.Mono;

import java.util.List;

/**
 * @author krissadewo
 * @date 2/4/22 3:32 PM
 */
public interface Save {

    Mono<List<StockDto>> execute(List<OrderDto> dtos);

}
