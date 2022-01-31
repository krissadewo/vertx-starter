package id.or.greenlabs.vertx.starter.module.category;

import com.google.inject.AbstractModule;
import id.or.greenlabs.vertx.starter.module.category.repository.CategoryRepository;
import id.or.greenlabs.vertx.starter.module.category.repository.CategoryRepositoryImpl;

/**
 * @author krissadewo
 * @date 1/21/22 1:17 PM
 */
public class CategoryModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(CategoryRepository.class).to(CategoryRepositoryImpl.class);
    }
}
