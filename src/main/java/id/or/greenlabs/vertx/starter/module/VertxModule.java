package id.or.greenlabs.vertx.starter.module;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import id.or.greenlabs.vertx.starter.common.Environment;
import io.vertx.core.Vertx;
import io.vertx.ext.web.Router;

import javax.inject.Named;

/**
 * @author krissadewo
 * @date 1/21/22 1:13 PM
 */
public class VertxModule extends AbstractModule {

    private final Vertx vertx;

    private Router router;

    private Environment env;

    public VertxModule(Vertx vertx) {
        this.vertx = vertx;
    }

    public VertxModule(Vertx vertx, Environment env) {
        this.vertx = vertx;
        this.env = env;
    }

    public VertxModule(Vertx vertx, Router router) {
        this.vertx = vertx;
        this.router = router;
    }

    public VertxModule(Vertx vertx, Router router, Environment env) {
        this.vertx = vertx;
        this.router = router;
        this.env = env;
    }

    @Provides
    @Named("env")
    Environment env() {
        return this.env;
    }

    @Provides
    @Named("vertx")
    Vertx vertx() {
        return this.vertx;
    }

    @Provides
    @Named("router")
    Router router() {
        return this.router;
    }

}
