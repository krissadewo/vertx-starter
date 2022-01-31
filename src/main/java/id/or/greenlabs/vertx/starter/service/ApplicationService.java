package id.or.greenlabs.vertx.starter.service;

import id.or.greenlabs.vertx.starter.common.Environment;
import id.or.greenlabs.vertx.starter.config.EnvironmentConfig;
import id.or.greenlabs.vertx.starter.config.JaegerConfig;
import id.or.greenlabs.vertx.starter.context.ApplicationContext;
import io.opentracing.Tracer;
import io.vertx.core.Vertx;

/**
 * @author krissadewo
 * @date 1/19/22 1:44 PM
 */
public class ApplicationService {

    protected final Environment env;

    protected ApplicationContext context;

    protected Tracer tracer;

    protected Vertx vertx;

    public ApplicationService(Vertx vertx) {
        this.vertx = vertx;

        this.context = new ApplicationContext(vertx);
        this.env = context.get(EnvironmentConfig.class).getEnv();

        if (context.get(JaegerConfig.class) != null) {
            this.tracer = context.get(JaegerConfig.class).getTracer();
        }
    }
}
