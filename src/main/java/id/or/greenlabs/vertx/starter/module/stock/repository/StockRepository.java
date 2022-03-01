package id.or.greenlabs.vertx.starter.module.stock.repository;

import id.or.greenlabs.vertx.starter.document.Stock;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

/**
 * @author krissadewo
 * @date 2/4/22 1:32 PM
 */
public interface StockRepository {

    Mono<List<Stock>> save(List<Stock> documents);

    Flux<Stock> findBy(Stock stock, int limit, int offset);
}
