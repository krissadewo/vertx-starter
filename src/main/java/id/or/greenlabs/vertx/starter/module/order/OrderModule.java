package id.or.greenlabs.vertx.starter.module.order;

import com.google.inject.AbstractModule;
import id.or.greenlabs.vertx.starter.module.order.repository.OrderRepository;
import id.or.greenlabs.vertx.starter.module.order.repository.OrderRepositoryImpl;

/**
 * @author krissadewo
 * @date 2/3/22 8:47 AM
 */
public class OrderModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(OrderRepository.class).to(OrderRepositoryImpl.class);
    }
}
