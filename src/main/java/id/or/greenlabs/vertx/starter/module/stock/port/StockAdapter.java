package id.or.greenlabs.vertx.starter.module.stock.port;

import id.or.greenlabs.vertx.starter.assembler.dto.StockDto;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

/**
 * @author krissadewo
 * @date 2/4/22 1:13 PM
 */
public interface StockAdapter {

    Mono<List<StockDto>> save(List<StockDto> dtos);

    Flux<StockDto> find(StockDto param, int limit, int offset);
}
