package id.or.greenlabs.vertx.starter.module.category.usecase;

import id.or.greenlabs.vertx.starter.assembler.dto.CategoryDto;
import id.or.greenlabs.vertx.starter.common.DefaultException;
import id.or.greenlabs.vertx.starter.common.StatusCode;
import io.vertx.junit5.VertxTestContext;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Mono;

/**
 * @author krissadewo
 * @date 2/9/22 10:48 AM
 */
class CategoryUseCaseTest extends BaseTest {

    private Save save;

    private Find find;

    private CategoryDto category;

    @Override
    protected void initInjector(VertxTestContext context) {
        save = injector.getProvider(Save.class).get();
        find = injector.getProvider(Find.class).get();
    }

    @Order(1)
    @Test
    void save(VertxTestContext context) {
        save.execute(dummyData.categoryDto())
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

    @Order(2)
    @Test
    void findAll(VertxTestContext context) {
        find.execute(new CategoryDto(), 10, 0)
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
