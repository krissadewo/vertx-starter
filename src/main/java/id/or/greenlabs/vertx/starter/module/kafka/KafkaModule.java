package id.or.greenlabs.vertx.starter.module.kafka;

import com.google.inject.AbstractModule;
import id.or.greenlabs.vertx.starter.module.kafka.listener.KafkaEvbListener;
import id.or.greenlabs.vertx.starter.module.kafka.port.KafkaAdapter;
import id.or.greenlabs.vertx.starter.module.kafka.service.KafkaService;
import id.or.greenlabs.vertx.starter.module.kafka.usecase.SendOrder;
import id.or.greenlabs.vertx.starter.module.kafka.usecase.impl.SendOrderImpl;

/**
 * @author krissadewo
 * @date 2/4/22 10:17 AM
 */
public class KafkaModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(KafkaEvbListener.class).asEagerSingleton();
        bind(KafkaAdapter.class).to(KafkaService.class);
        bind(SendOrder.class).to(SendOrderImpl.class);
    }
}
