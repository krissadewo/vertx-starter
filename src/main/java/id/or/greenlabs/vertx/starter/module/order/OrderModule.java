package id.or.greenlabs.vertx.starter.module.order;

import com.google.inject.AbstractModule;
import id.or.greenlabs.vertx.starter.module.order.port.KafkaAdapter;
import id.or.greenlabs.vertx.starter.module.order.port.OrderAdapter;
import id.or.greenlabs.vertx.starter.module.order.repository.OrderRepository;
import id.or.greenlabs.vertx.starter.module.order.repository.OrderRepositoryImpl;
import id.or.greenlabs.vertx.starter.module.order.service.KafkaService;
import id.or.greenlabs.vertx.starter.module.order.service.OrderService;
import id.or.greenlabs.vertx.starter.module.order.usecase.OrderProduct;
import id.or.greenlabs.vertx.starter.module.order.usecase.impl.OrderProductImpl;

/**
 * @author krissadewo
 * @date 2/3/22 8:47 AM
 */
public class OrderModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(OrderRepository.class).to(OrderRepositoryImpl.class);
        bind(OrderAdapter.class).to(OrderService.class);
        bind(OrderProduct.class).to(OrderProductImpl.class);
        bind(KafkaAdapter.class).to(KafkaService.class);
    }
}
