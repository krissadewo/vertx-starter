package id.or.greenlabs.vertx.starter.listener;

import io.vertx.core.Vertx;

/**
 * @author krissadewo
 * @date 2/4/22 10:19 AM
 */
public abstract class ApplicationListener {

    protected Vertx vertx;

    public ApplicationListener(Vertx vertx) {
        this.vertx = vertx;

        eventBusListener();
    }

    protected abstract void eventBusListener();
}
