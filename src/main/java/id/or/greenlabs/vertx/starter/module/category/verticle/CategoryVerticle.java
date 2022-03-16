package id.or.greenlabs.vertx.starter.module.category.verticle;

import com.google.inject.Guice;
import id.or.greenlabs.vertx.starter.ApplicationVerticle;
import id.or.greenlabs.vertx.starter.config.MongoConfig;
import id.or.greenlabs.vertx.starter.module.VertxModule;
import id.or.greenlabs.vertx.starter.module.category.CategoryModule;
import id.or.greenlabs.vertx.starter.module.category.service.CategoryService;
import id.or.greenlabs.vertx.starter.repository.MongoModule;
import io.vertx.ext.web.Router;

/**
 * @author krissadewo
 * @date 2/10/22 11:00 AM
 */
public class CategoryVerticle extends ApplicationVerticle<CategoryService> {

    private final Router router;

    public CategoryVerticle(Router router) {
        this.router = router;
    }

    @Override
    protected void buildModule() {
        Guice.createInjector(
            new VertxModule(vertx, router),
            new CategoryModule(),
            new MongoModule(context.get(MongoConfig.class))
        );
    }
}
