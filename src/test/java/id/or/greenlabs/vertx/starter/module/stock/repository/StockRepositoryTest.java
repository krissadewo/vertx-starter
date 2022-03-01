package id.or.greenlabs.vertx.starter.module.stock.repository;

import id.or.greenlabs.vertx.starter.assembler.wrapper.OrderWrapper;
import id.or.greenlabs.vertx.starter.common.DefaultException;
import id.or.greenlabs.vertx.starter.common.StatusCode;
import id.or.greenlabs.vertx.starter.module.order.repository.OrderRepository;
import id.or.greenlabs.vertx.starter.module.order.repository.OrderRepositoryImpl;
import io.vertx.junit5.VertxTestContext;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

/**
 * @author krissadewo
 * @date 2/4/22 1:41 PM
 */
class StockRepositoryTest extends BaseTest {

    private StockRepository stockRepository;

    private OrderRepository orderRepository;

    private static List<id.or.greenlabs.vertx.starter.document.Order> orders;

    @Override
    protected void initInjector(VertxTestContext context) {
        stockRepository = injector.getProvider(StockRepositoryImpl.class).get();
        orderRepository = injector.getProvider(OrderRepositoryImpl.class).get();
    }

    @Order(0)
    @Test
    void saveOrder(VertxTestContext context) {
        orderRepository.save(new ArrayList<>(new OrderWrapper().toDocument(dummyData.orders())))
            .switchIfEmpty(Mono.error(new DefaultException(StatusCode.OPERATION_FAILED)))
            .flatMap(object -> {
                if (!object.isEmpty()) {
                    orders = object;
                }

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
    void saveStock(VertxTestContext context) {
        stockRepository.save(dummyData.stocks(orders)).switchIfEmpty(Mono.error(new DefaultException(StatusCode.OPERATION_FAILED)))
            .flatMap(object -> {
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
        stockRepository.findBy(null, 10, 0)
            .switchIfEmpty(Mono.error(new DefaultException(StatusCode.OPERATION_FAILED)))
            .flatMap(order -> {
                return Flux.just(order);
            })
            .doFinally(signalType -> {
                context.completeNow();
            })
            .doOnError(context::failNow)
            .subscribe();
    }

}
