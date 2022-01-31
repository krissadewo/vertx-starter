package id.or.greenlabs.vertx.starter.config;

import id.or.greenlabs.vertx.starter.common.Environment;
import id.or.greenlabs.vertx.starter.context.ShareableContext;
import io.vertx.core.Vertx;
import io.vertx.core.json.Json;
import io.vertx.core.json.JsonObject;
import lombok.Builder;
import lombok.Getter;

/**
 * @author krissadewo
 * @date 1/19/22 1:48 PM
 */
@Getter
public class EnvironmentConfig extends ShareableContext<EnvironmentConfig> {

    private Environment env;

    @Builder
    public EnvironmentConfig(Vertx vertx, JsonObject config) {
        super(vertx);

        createOrUpdateEnvironment(config);
    }

    public void createOrUpdateEnvironment(JsonObject config) {
        env = Json.decodeValue(config.encode(), Environment.class);
    }

    @Override
    protected EnvironmentConfig registerConfig() {
        return this;
    }
}
