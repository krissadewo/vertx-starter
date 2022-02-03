package id.or.greenlabs.vertx.starter.module.order.repository;

import id.or.greenlabs.vertx.starter.common.DefaultException;
import id.or.greenlabs.vertx.starter.common.StatusCode;
import id.or.greenlabs.vertx.starter.document.Product;
import id.or.greenlabs.vertx.starter.module.product.repository.ProductRepository;
import id.or.greenlabs.vertx.starter.module.product.repository.ProductRepositoryImpl;
import io.vertx.junit5.VertxTestContext;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Mono;

/**
 * @author krissadewo
 * @date 2/3/22 10:53 AM
 */
class OrderRepositoryTest extends BaseTest {

    private OrderRepository orderRepository;

    private ProductRepository productRepository;

    private static id.or.greenlabs.vertx.starter.document.Order order;

    private static Product product;

    @BeforeAll
    @Override
    protected void initInjector() {
        orderRepository = injector.getProvider(OrderRepositoryImpl.class).get();
        productRepository = injector.getProvider(ProductRepositoryImpl.class).get();
    }

    @Order(0)
    @Test
    void saveProduct(final VertxTestContext context) {
        productRepository.save(dummyData.product(null))
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

    @Order(1)
    @Test
    void saveOrder(final VertxTestContext context) {
        orderRepository.save(dummyData.order(product))
            .switchIfEmpty(Mono.error(new DefaultException(StatusCode.OPERATION_FAILED)))
            .flatMap(object -> {
                order = object;

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
    void find(final VertxTestContext context) {
        orderRepository.find(null, 10, 0)
            .switchIfEmpty(Mono.error(new DefaultException(StatusCode.OPERATION_FAILED)))
            .flatMap(Mono::just)
            .doFinally(signalType -> {
                context.completeNow();
            })
            .doOnError(context::failNow)
            .subscribe();
    }

}
