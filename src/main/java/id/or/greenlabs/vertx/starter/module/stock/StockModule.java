package id.or.greenlabs.vertx.starter.module.stock;

import com.google.inject.AbstractModule;
import id.or.greenlabs.vertx.starter.module.stock.listener.StockEvbListener;
import id.or.greenlabs.vertx.starter.module.stock.port.StockAdapter;
import id.or.greenlabs.vertx.starter.module.stock.repository.StockRepository;
import id.or.greenlabs.vertx.starter.module.stock.repository.StockRepositoryImpl;
import id.or.greenlabs.vertx.starter.module.stock.service.StockService;
import id.or.greenlabs.vertx.starter.module.stock.usecase.Save;
import id.or.greenlabs.vertx.starter.module.stock.usecase.impl.SaveImpl;

/**
 * @author krissadewo
 * @date 2/3/22 8:54 AM
 */
public class StockModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(StockRepository.class).to(StockRepositoryImpl.class);
        bind(StockAdapter.class).to(StockService.class);
        bind(Save.class).to(SaveImpl.class);
        bind(StockEvbListener.class).asEagerSingleton();
    }
}
