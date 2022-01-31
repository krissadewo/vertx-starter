package id.or.greenlabs.vertx.starter.repository;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.name.Named;
import id.or.greenlabs.vertx.starter.config.MongoConfig;

/**
 * @author krissadewo
 * @date 1/24/22 10:52 AM
 */
public class MongoModule extends AbstractModule {

    private final MongoConfig mongoConfig;

    public MongoModule(MongoConfig mongoConfig) {
        this.mongoConfig = mongoConfig;
    }

    @Provides
    @Named("mongoConfig")
    MongoConfig mongoConfig() {
        return this.mongoConfig;
    }
}
