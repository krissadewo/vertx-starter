package id.or.greenlabs.vertx.starter.module.category.repository;

import com.google.inject.Guice;
import com.google.inject.Injector;
import id.or.greenlabs.vertx.starter.AbstractGenericTest;
import id.or.greenlabs.vertx.starter.module.VertxModule;
import id.or.greenlabs.vertx.starter.module.category.CategoryModule;
import id.or.greenlabs.vertx.starter.repository.MongoModule;
import io.vertx.core.Vertx;
import io.vertx.ext.web.Router;
import io.vertx.junit5.VertxTestContext;

/**
 * @author krissadewo
 * @date 1/21/22 1:15 PM
 */
public abstract class BaseTest extends AbstractGenericTest {

    protected Injector injector;

    protected void prepareBuilder(Vertx vertx, VertxTestContext testContext) {
        injector = Guice.createInjector(
            new VertxModule(vertx, Router.router(vertx), environmentConfig.getEnv()),
            new MongoModule(mongoConfig),
            new CategoryModule()
        );
    }

}
