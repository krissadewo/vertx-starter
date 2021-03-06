package id.or.greenlabs.vertx.starter;

import id.or.greenlabs.vertx.starter.common.DummyData;
import id.or.greenlabs.vertx.starter.common.codec.GenericCodec;
import id.or.greenlabs.vertx.starter.common.codec.RequestParamCodec;
import id.or.greenlabs.vertx.starter.common.subscribe.SingleSubscriber;
import id.or.greenlabs.vertx.starter.config.EnvironmentConfig;
import id.or.greenlabs.vertx.starter.config.MongoConfig;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;
import io.vertx.junit5.VertxExtension;
import io.vertx.junit5.VertxTestContext;
import org.apache.commons.io.IOUtils;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;

import java.io.FileReader;
import java.io.IOException;

/**
 * @author krissadewo
 * @date 1/21/22 1:47 PM
 */
@ExtendWith(VertxExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public abstract class AbstractCommonTest {

    protected JsonObject config;

    protected MongoConfig mongoConfig;

    protected EnvironmentConfig environmentConfig;

    protected DummyData dummyData = new DummyData();

    @BeforeAll
    void init(Vertx vertx, VertxTestContext context) throws IOException {
        config = new JsonObject(IOUtils.toString(new FileReader("src/main/resources/env/test/bootstrap.json")));

        environmentConfig = EnvironmentConfig.builder()
            .vertx(vertx)
            .config(config)
            .build();

        mongoConfig = MongoConfig.builder()
            .vertx(vertx)
            .env(environmentConfig.getEnv())
            .build();

        vertx.eventBus().registerCodec(new GenericCodec());
        vertx.eventBus().registerCodec(new RequestParamCodec());

        prepareBuilder(vertx, context);

        initInjector(context);

        context.completeNow();
    }

    protected abstract void initInjector(VertxTestContext context);

    protected abstract void prepareBuilder(Vertx vertx, VertxTestContext testContext);

    @Order(-99)
    @Test
    public void setupCollection(final VertxTestContext context) {
        mongoConfig.getDatabase().drop()
            .subscribe(new SingleSubscriber<>() {
                @Override
                public void onSuccess(Void result) {
                    context.completeNow();
                }

                @Override
                public void onFailure(Throwable throwable) {

                }
            });
    }
}
