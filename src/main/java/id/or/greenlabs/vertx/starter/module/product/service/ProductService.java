package id.or.greenlabs.vertx.starter.module.product.service;

import id.or.greenlabs.vertx.starter.assembler.dto.ProductDto;
import id.or.greenlabs.vertx.starter.assembler.wrapper.ProductWrapper;
import id.or.greenlabs.vertx.starter.common.StatusCode;
import id.or.greenlabs.vertx.starter.document.Product;
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
    public Mono<Product> save(ProductDto document) {
        return repository.save(new ProductWrapper().toDocument(document));
    }

    @Override
    public Mono<Product> find(String id) {
        return repository.find(id);
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
    public Flux<Product> find(ProductDto param, int limit, int offset) {
        return repository.find(new ProductWrapper().toParam(param), limit, offset);
    }
}
