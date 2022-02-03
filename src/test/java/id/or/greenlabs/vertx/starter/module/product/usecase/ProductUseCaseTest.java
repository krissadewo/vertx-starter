package id.or.greenlabs.vertx.starter.module.product.usecase;

import id.or.greenlabs.vertx.starter.assembler.dto.ProductDto;
import id.or.greenlabs.vertx.starter.common.DefaultException;
import id.or.greenlabs.vertx.starter.common.StatusCode;
import id.or.greenlabs.vertx.starter.module.product.usecase.impl.FindImpl;
import id.or.greenlabs.vertx.starter.module.product.usecase.impl.FindByIdImpl;
import id.or.greenlabs.vertx.starter.module.product.usecase.impl.SaveImpl;
import io.vertx.junit5.VertxTestContext;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Mono;

/**
 * @author krissadewo
 * @date 1/31/22 2:30 PM
 */
class ProductUseCaseTest extends BaseTest {

    private Save save;

    private FindById findById;

    private Find find;

    private static ProductDto product;

    @BeforeAll
    @Override
    protected void initInjector() {
        save = injector.getProvider(SaveImpl.class).get();
        findById = injector.getProvider(FindByIdImpl.class).get();
        find = injector.getProvider(FindImpl.class).get();
    }

    @Order(1)
    @Test
    void save(VertxTestContext context) {
        save.execute(dummyData.productDto())
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
    void findById(VertxTestContext context) {
        findById.execute(product.getId())
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

    @Order(3)
    @Test
    void findAll(VertxTestContext context) {
        find.execute(dummyData.productDto(),10,0)
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
