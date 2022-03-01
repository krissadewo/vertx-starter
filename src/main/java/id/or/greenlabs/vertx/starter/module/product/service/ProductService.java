package id.or.greenlabs.vertx.starter.module.product.service;

import id.or.greenlabs.vertx.starter.assembler.dto.ProductDto;
import id.or.greenlabs.vertx.starter.assembler.wrapper.ProductWrapper;
import id.or.greenlabs.vertx.starter.common.StatusCode;
import id.or.greenlabs.vertx.starter.module.product.port.ProductAdapter;
import id.or.greenlabs.vertx.starter.module.product.repository.ProductRepository;
import id.or.greenlabs.vertx.starter.service.ApplicationService;
import io.vertx.core.Vertx;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.inject.Inject;
import javax.inject.Named;

/**
 * @author krissadewo
 * @date 1/31/22 12:50 PM
 */
public class ProductService extends ApplicationService implements ProductAdapter {

    private final ProductRepository repository;

    @Inject
    public ProductService(@Named("vertx") Vertx vertx, ProductRepository repository) {
        super(vertx);

        this.repository = repository;
    }

    @Override
    public Mono<ProductDto> save(ProductDto document) {
        return repository.save(new ProductWrapper().toDocument(document))
            .map(product -> {
                return new ProductWrapper().toDto(product);
            });
    }

    @Override
    public Mono<ProductDto> findBy(String id) {
        return repository.findBy(id)
            .map(product -> new ProductWrapper().toDto(product));
    }

    @Override
    public Mono<String> delete(String id) {
        return repository.delete(id)
            .flatMap(updateResult -> {
                if (updateResult.getModifiedCount() > 0) {
                    return Mono.just(StatusCode.OPERATION_SUCCESS);
                }

                return Mono.just(StatusCode.OPERATION_FAILED);
            });
    }

    @Override
    public Flux<ProductDto> findBy(ProductDto param, int limit, int offset) {
        return repository.findBy(new ProductWrapper().toParam(param), limit, offset)
            .map(product -> new ProductWrapper().toDto(product));
    }
}
