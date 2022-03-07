package id.or.greenlabs.vertx.starter.module.stock.service;

import id.or.greenlabs.vertx.starter.assembler.dto.StockDto;
import id.or.greenlabs.vertx.starter.assembler.wrapper.StockWrapper;
import id.or.greenlabs.vertx.starter.module.stock.port.StockAdapter;
import id.or.greenlabs.vertx.starter.module.stock.repository.StockRepository;
import id.or.greenlabs.vertx.starter.service.ApplicationService;
import io.vertx.core.Vertx;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.ArrayList;
import java.util.List;

/**
 * @author krissadewo
 * @date 2/4/22 1:31 PM
 */
public class StockService extends ApplicationService implements StockAdapter {

    private final StockRepository repository;

    @Inject
    public StockService(@Named("vertx") Vertx vertx, StockRepository repository) {
        super(vertx);

        this.repository = repository;
    }

    @Override
    public Mono<List<StockDto>> save(List<StockDto> dtos) {
        return repository.save(new ArrayList<>(new StockWrapper().toDocument(dtos)))
            .map(result -> {
                return new ArrayList<>(new StockWrapper().toDto(result));
            });
    }

    @Override
    public Flux<StockDto> findBy(StockDto param, int limit, int offset) {
        return repository.findBy(new StockWrapper().toParam(param), limit, offset)
            .map(result -> {
                return new StockWrapper().toDto(result);
            });
    }
}
