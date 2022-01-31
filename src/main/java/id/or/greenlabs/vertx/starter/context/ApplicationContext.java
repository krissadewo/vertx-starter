package id.or.greenlabs.vertx.starter.context;

import io.vertx.core.Vertx;
import io.vertx.core.shareddata.LocalMap;

import java.util.function.Supplier;

/**
 * @author krissadewo
 * @date 1/19/22 1:43 PM
 */
public class ApplicationContext {

    final Object lock = new Object();

    private final LocalMap<String, Object> sharedConfig;

    public ApplicationContext(Vertx vertx) {
        sharedConfig = vertx.sharedData().getLocalMap("application-context");
    }

    public <T> void putIfAbsent(Class<T> clazz, Supplier<T> supplier) {
        synchronized (lock) {
            if (get(clazz) == null) {
                sharedConfig.put(clazz.getName(), supplier.get());
            }

            get(clazz);
        }
    }

    public <T> T get(Class<T> clazz) {
        return clazz.cast(sharedConfig.get(clazz.getName()));
    }
}
