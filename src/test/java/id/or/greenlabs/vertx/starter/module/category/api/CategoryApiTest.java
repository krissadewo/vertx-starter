package id.or.greenlabs.vertx.starter.module.category.api;

import id.or.greenlabs.vertx.starter.AbstractApiTest;
import id.or.greenlabs.vertx.starter.common.DummyData;
import id.or.greenlabs.vertx.starter.common.StatusCode;
import id.or.greenlabs.vertx.starter.module.category.verticle.CategoryVerticle;
import io.vertx.core.DeploymentOptions;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.client.WebClient;
import io.vertx.ext.web.codec.BodyCodec;
import io.vertx.junit5.Checkpoint;
import io.vertx.junit5.VertxTestContext;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

/**
 * @author krissadewo
 * @date 2/10/22 10:55 AM
 */
class CategoryApiTest extends AbstractApiTest {

    @Override
    protected void prepareBuilder(Vertx vertx, VertxTestContext testContext) {
        DeploymentOptions options = new DeploymentOptions();
        options.setConfig(config);

        Router router = Router.router(vertx);

        vertx.createHttpServer()
            .requestHandler(router)
            .listen(environmentConfig.getEnv().getServicePort(), http -> {
                if (http.succeeded()) {
                    testContext.assertComplete(vertx.deployVerticle(new CategoryVerticle(router, mongoConfig)));
                } else {
                    testContext.failingThenComplete();
                }
            });
    }

    @Order(1)
    @Test
    void save(Vertx vertx, VertxTestContext testContext) {
        WebClient webClient = WebClient.create(vertx, opts);
        Checkpoint requestCheckpoint = testContext.checkpoint(1);

        webClient.post("/categories")
            .as(BodyCodec.json(Object.class))
            .sendJsonObject(JsonObject.mapFrom(new DummyData().categoryDto()), testContext.succeeding(response -> testContext.verify(() -> {
                    JsonObject object = JsonObject.mapFrom(response.body());

                    Assertions.assertEquals(object.getString("status"), StatusCode.OPERATION_SUCCESS);
                    Assertions.assertEquals(response.statusCode(), 200);

                    requestCheckpoint.flag();
                }))
            );
    }

    @Order(2)
    @Test
    void find(Vertx vertx, VertxTestContext testContext) {
        WebClient webClient = WebClient.create(vertx, opts);
        Checkpoint requestCheckpoint = testContext.checkpoint(1);

        webClient.get("/categories?limit=10&offset=0")
            .as(BodyCodec.json(Object.class))
            .send(testContext.succeeding(response -> testContext.verify(() -> {
                    JsonObject object = JsonObject.mapFrom(response.body());

                    Assertions.assertEquals(object.getString("status"), StatusCode.OPERATION_SUCCESS);
                    Assertions.assertEquals(response.statusCode(), 200);

                    requestCheckpoint.flag();
                }))
            );
    }

}
