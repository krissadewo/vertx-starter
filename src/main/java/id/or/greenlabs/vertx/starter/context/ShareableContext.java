package id.or.greenlabs.vertx.starter.context;

import io.vertx.core.Vertx;
import io.vertx.core.shareddata.Shareable;

/**
 * @author krissadewo
 * @date 1/19/22 1:49 PM
 */
public abstract class ShareableContext<T> implements Shareable {

    T config;

    Vertx vertx;

    /**
     * @param vertx
     */
    public ShareableContext(Vertx vertx) {
        this.vertx = vertx;
        this.config = registerConfig();

        make();
    }

    private void make() {
        ApplicationContext object = new ApplicationContext(vertx);
        object.putIfAbsent((Class<T>) config.getClass(), this::registerConfig);
    }

    protected abstract T registerConfig();
}
