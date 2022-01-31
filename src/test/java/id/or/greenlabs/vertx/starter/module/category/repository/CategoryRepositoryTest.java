package id.or.greenlabs.vertx.starter.module.category.repository;

import id.or.greenlabs.vertx.starter.common.DefaultException;
import id.or.greenlabs.vertx.starter.common.StatusCode;
import id.or.greenlabs.vertx.starter.document.Category;
import io.vertx.junit5.VertxTestContext;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Mono;

/**
 * @author krissadewo
 * @date 1/21/22 1:14 PM
 */
class CategoryRepositoryTest extends BaseTest {

    protected CategoryRepository categoryRepository;

    private static Category category;

    @Override
    @BeforeAll
    protected void initInjector() {
        categoryRepository = injector.getProvider(CategoryRepositoryImpl.class).get();
    }

    @Order(1)
    @Test
    void save(VertxTestContext context) {
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

    @Order(2)
    @Test
    void find(VertxTestContext context) {
        categoryRepository.find(category.getId().toHexString())
            .switchIfEmpty(Mono.error(new DefaultException(StatusCode.DATA_NOT_FOUND)))
            .flatMap(Mono::just)
            .doFinally(signalType -> {
                context.completeNow();
            })
            .doOnError(context::failNow)
            .subscribe();
    }

}
