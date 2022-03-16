package id.or.greenlabs.vertx.starter;

import id.or.greenlabs.vertx.starter.common.codec.GenericCodec;
import id.or.greenlabs.vertx.starter.common.codec.RequestParamCodec;
import id.or.greenlabs.vertx.starter.config.EnvironmentConfig;
import id.or.greenlabs.vertx.starter.config.MongoConfig;
import id.or.greenlabs.vertx.starter.context.ApplicationContext;
import id.or.greenlabs.vertx.starter.module.category.verticle.CategoryVerticle;
import id.or.greenlabs.vertx.starter.module.kafka.verticle.KafkaVerticle;
import io.vertx.core.*;
import io.vertx.ext.web.Router;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.List;

public class MainVerticle extends AbstractVerticle {

    private final Logger logger = LoggerFactory.getLogger(MainVerticle.class);

    private EnvironmentConfig config;

    @Override
    public void start(Promise<Void> startPromise) throws Exception {
        String env = config().getString("active.profile", null);//fill it with default value

        if (env == null) {
            logger.error("Run with {}", "run com.xxx.xxx.xxx.MainVerticle -conf src/main/resources/env/local/config.json");

            throw new RuntimeException();
        }

        setupVertxEnv();

        bootstrapApplication(Router.router(vertx), startPromise);
    }

    private void bootstrapApplication(Router router, Promise<Void> startPromise) {
        vertx.createHttpServer()
            .requestHandler(router)
            .listen(config.getEnv().getServicePort(), http -> {
                if (http.succeeded()) {
                    startPromise.complete();

                    List<Future> listOfFuture = Arrays.asList(
                        deployCategoryVerticle(router),
                        deployKafkaVerticle()
                    );

                    CompositeFuture.all(listOfFuture)
                        .onComplete(event -> {
                            if (event.succeeded()) {
                                logger.info("success deploying all verticles");
                            } else {
                                logger.error("failed deploying verticle {}", event.result().list());
                            }
                        });

                    logger.info("HTTP server started on port 8019");
                } else {
                    startPromise.fail(http.cause());
                }
            });
    }

    private void setupVertxEnv() {
        config = new ApplicationContext(vertx).get(EnvironmentConfig.class);

        buildEnvironment(vertx);
        buildMongodb(vertx);

        vertx.eventBus().registerCodec(new RequestParamCodec());
        vertx.eventBus().registerCodec(new GenericCodec());
    }

    private Future<String> deployCategoryVerticle(Router router) {
        return vertx.deployVerticle(new CategoryVerticle(router));
    }

    private Future<String> deployKafkaVerticle() {
        DeploymentOptions options = new DeploymentOptions();
        options.setWorker(true);

        return vertx.deployVerticle(new KafkaVerticle(config.getEnv()), options);
    }

    private void buildMongodb(Vertx vertx) {
       MongoConfig.builder()
            .vertx(vertx)
            .env(config.getEnv())
            .build();
    }

    private void buildEnvironment(Vertx vertx) {
        EnvironmentConfig.builder()
            .vertx(vertx)
            .config(config())
            .build();
    }
}
