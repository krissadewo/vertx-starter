package id.or.greenlabs.vertx.starter.module.product.repository;

import id.or.greenlabs.vertx.starter.common.DefaultException;
import id.or.greenlabs.vertx.starter.common.StatusCode;
import id.or.greenlabs.vertx.starter.document.Category;
import id.or.greenlabs.vertx.starter.document.Product;
import id.or.greenlabs.vertx.starter.module.category.repository.CategoryRepository;
import id.or.greenlabs.vertx.starter.module.category.repository.CategoryRepositoryImpl;
import io.vertx.junit5.VertxTestContext;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * @author krissadewo
 * @date 1/24/22 10:02 AM
 */
class ProductRepositoryTest extends BaseTest {

    private ProductRepository productRepository;

    private CategoryRepository categoryRepository;

    private Product product;

    private Category category;

    @Override
    protected void initInjector(VertxTestContext context) {
        productRepository = injector.getProvider(ProductRepositoryImpl.class).get();
        categoryRepository = injector.getProvider(CategoryRepositoryImpl.class).get();
    }

    @Order(0)
    @Test
    void saveCategory(final VertxTestContext context) {
        categoryRepository.save(dummyData.category())
            .switchIfEmpty(Mono.error(new DefaultException(StatusCode.OPERATION_FAILED)))
            .flatMap(object -> {
                category = object;

                return Mono.just(object);
            })
            .doFinally(signalType -> {
                context.completeNow();
            })
            .doOnError(context::failNow)
            .subscribe();
    }

    @Order(1)
    @Test
    void saveProduct(VertxTestContext context) {
        productRepository.save(dummyData.product(category.getId()))
            .switchIfEmpty(Mono.error(new DefaultException(StatusCode.OPERATION_FAILED)))
            .flatMap(object -> {
                product = object;

                return Mono.just(object);
            })
            .doFinally(signalType -> {
                context.completeNow();
            })
            .doOnError(context::failNow)
            .subscribe();
    }


    @Order(2)
    @Test
    void find(VertxTestContext context) {
        productRepository.findBy(new Product(), 10, 0)
            .switchIfEmpty(Mono.error(new DefaultException(StatusCode.DATA_NOT_FOUND)))
            .flatMap(result -> {
                if (result.getCategory().getId() == null) {
                    return Flux.error(new DefaultException(StatusCode.DATA_NOT_FOUND));
                }

                return Flux.just(result);
            })
            .doFinally(signalType -> {
                context.completeNow();
            })
            .doOnError(context::failNow)
            .subscribe();
    }

    @Order(3)
    @Test
    void findById(VertxTestContext context) {
        productRepository.findBy(product.getId().toHexString())
            .switchIfEmpty(Mono.error(new DefaultException(StatusCode.DATA_NOT_FOUND)))
            .flatMap(Mono::just)
            .doFinally(signalType -> {
                context.completeNow();
            })
            .doOnError(context::failNow)
            .subscribe();
    }


    @Order(4)
    @Test
    void delete(VertxTestContext context) {
        productRepository.delete(product.getId().toHexString())
            .switchIfEmpty(Mono.error(new DefaultException(StatusCode.DATA_NOT_FOUND)))
            .flatMap(result -> {
                if (result.getModifiedCount() > 0) {
                    return Mono.just(StatusCode.OPERATION_SUCCESS);
                }

                return Mono.just(new DefaultException(StatusCode.OPERATION_FAILED));
            })
            .doFinally(signalType -> {
                context.completeNow();
            })
            .doOnError(context::failNow)
            .subscribe();
    }

}
