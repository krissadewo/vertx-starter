package id.or.greenlabs.vertx.starter.config;

import id.or.greenlabs.vertx.starter.common.Environment;
import id.or.greenlabs.vertx.starter.context.ShareableContext;
import io.jaegertracing.Configuration;
import io.jaegertracing.internal.samplers.ConstSampler;
import io.opentracing.Tracer;
import io.vertx.core.Vertx;
import lombok.Builder;
import lombok.Getter;

/**
 * @author krissadewo
 * @date 1/19/22 1:52 PM
 */
@Getter
public class JaegerConfig extends ShareableContext<JaegerConfig> {

    Tracer tracer;

    @Builder
    public JaegerConfig(Vertx vertx, Environment env) {
        super(vertx);

        setupJaeger(env);
    }

    private void setupJaeger(Environment env) {
        tracer = new Configuration(env.getServiceName())
            .withReporter(
                new Configuration.ReporterConfiguration()
                    .withSender(new Configuration.SenderConfiguration().withAgentHost(env.getJaeger().get("host")).withAgentPort(6831))
                    .withLogSpans(true)
            )
            .withSampler(
                new Configuration.SamplerConfiguration()
                    .withType(ConstSampler.TYPE)
                    .withParam(1)
            )
            .getTracerBuilder()
            .build();
    }

    @Override
    protected JaegerConfig registerConfig() {
        return this;
    }
}
