package id.or.greenlabs.vertx.starter.repository;

import id.or.greenlabs.vertx.starter.config.MongoConfig;

/**
 * @author krissadewo
 * @date 1/21/22 12:55 PM
 */
public class GenericRepository {

    protected final MongoConfig mongoConfig;

    public GenericRepository(MongoConfig mongoConfig) {
        this.mongoConfig = mongoConfig;
    }
}
