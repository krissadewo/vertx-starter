package id.or.greenlabs.vertx.starter.module.product;

import com.google.inject.AbstractModule;
import id.or.greenlabs.vertx.starter.module.product.port.ProductAdapter;
import id.or.greenlabs.vertx.starter.module.product.repository.ProductRepository;
import id.or.greenlabs.vertx.starter.module.product.repository.ProductRepositoryImpl;
import id.or.greenlabs.vertx.starter.module.product.service.ProductService;
import id.or.greenlabs.vertx.starter.module.product.usecase.FindAll;
import id.or.greenlabs.vertx.starter.module.product.usecase.FindById;
import id.or.greenlabs.vertx.starter.module.product.usecase.Save;
import id.or.greenlabs.vertx.starter.module.product.usecase.impl.FindAllImpl;
import id.or.greenlabs.vertx.starter.module.product.usecase.impl.FindByIdImpl;
import id.or.greenlabs.vertx.starter.module.product.usecase.impl.SaveImpl;

/**
 * @author krissadewo
 * @date 1/24/22 9:50 AM
 */
public class ProductModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(ProductRepository.class).to(ProductRepositoryImpl.class);
        bind(ProductAdapter.class).to(ProductService.class);
        bind(Save.class).to(SaveImpl.class);
        bind(FindById.class).to(FindByIdImpl.class);
        bind(FindAll.class).to(FindAllImpl.class);
    }
}
