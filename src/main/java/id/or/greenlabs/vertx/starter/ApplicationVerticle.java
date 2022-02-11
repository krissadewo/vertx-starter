package id.or.greenlabs.vertx.starter;

import id.or.greenlabs.vertx.starter.service.ApplicationService;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author krissadewo
 * @date 2/10/22 11:02 AM
 */
public abstract class ApplicationVerticle<T extends ApplicationService> extends AbstractVerticle {

    protected T service;

    protected final Logger logger = LoggerFactory.getLogger(MainVerticle.class);

    @Override
    public void start(Promise<Void> startPromise) throws Exception {
        super.start(startPromise);

        buildModule();
    }

    protected abstract void buildModule();
}
