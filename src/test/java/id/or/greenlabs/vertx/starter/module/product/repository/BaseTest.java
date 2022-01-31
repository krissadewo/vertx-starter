package id.or.greenlabs.vertx.starter.module.product.repository;

import com.google.inject.Guice;
import com.google.inject.Injector;
import id.or.greenlabs.vertx.starter.AbstractGenericTest;
import id.or.greenlabs.vertx.starter.common.DummyData;
import id.or.greenlabs.vertx.starter.config.MongoConfig;
import id.or.greenlabs.vertx.starter.module.VertxModule;
import id.or.greenlabs.vertx.starter.module.category.CategoryModule;
import id.or.greenlabs.vertx.starter.module.product.ProductModule;
import id.or.greenlabs.vertx.starter.repository.MongoModule;
import io.vertx.core.Vertx;
import io.vertx.ext.web.Router;

/**
 * @author krissadewo
 * @date 1/21/22 1:15 PM
 */
public abstract class BaseTest extends AbstractGenericTest {

    protected Injector injector;

    protected DummyData dummyData = new DummyData();

    protected void prepareBuilder(Vertx vertx) {
        MongoConfig mongoConfig = MongoConfig.builder()
            .vertx(vertx)
            .env(environmentConfig.getEnv())
            .build();

        injector = Guice.createInjector(
            new VertxModule(vertx, Router.router(vertx), environmentConfig.getEnv()),
            new MongoModule(mongoConfig),
            new CategoryModule(),
            new ProductModule()
        );
    }

}