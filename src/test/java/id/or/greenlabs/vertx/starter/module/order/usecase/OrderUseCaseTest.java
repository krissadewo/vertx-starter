package id.or.greenlabs.vertx.starter.module.order.usecase;

import id.or.greenlabs.vertx.starter.assembler.dto.OrderDto;
import id.or.greenlabs.vertx.starter.common.DefaultException;
import id.or.greenlabs.vertx.starter.common.StatusCode;
import id.or.greenlabs.vertx.starter.module.order.usecase.impl.FindImpl;
import id.or.greenlabs.vertx.starter.module.order.usecase.impl.OrderProductImpl;
import io.vertx.junit5.VertxTestContext;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Mono;

/**
 * @author krissadewo
 * @date 2/3/22 7:04 PM
 */
class OrderUseCaseTest extends BaseTest {

    private OrderProduct orderProduct;

    private Find find;

    @BeforeAll
    @Override
    protected void initInjector() {
        orderProduct = injector.getProvider(OrderProductImpl.class).get();
        find = injector.getProvider(FindImpl.class).get();
    }

    @Order(0)
    @Test
    void order(VertxTestContext context) {
        orderProduct.execute(dummyData.orders())
            .switchIfEmpty(Mono.error(new DefaultException(StatusCode.OPERATION_FAILED)))
            .flatMap(object -> {
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
    void find(VertxTestContext context) {
        find.execute(new OrderDto(), 10, 0)
            .switchIfEmpty(Mono.error(new DefaultException(StatusCode.OPERATION_FAILED)))
            .flatMap(object -> {
                return Mono.just(object);
            })
            .doFinally(signalType -> {
                context.completeNow();
            })
            .doOnError(context::failNow)
            .subscribe();
    }
}
