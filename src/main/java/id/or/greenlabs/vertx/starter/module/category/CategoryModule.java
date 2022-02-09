package id.or.greenlabs.vertx.starter.module.category;

import com.google.inject.AbstractModule;
import id.or.greenlabs.vertx.starter.module.category.port.CategoryAdapter;
import id.or.greenlabs.vertx.starter.module.category.repository.CategoryRepository;
import id.or.greenlabs.vertx.starter.module.category.repository.CategoryRepositoryImpl;
import id.or.greenlabs.vertx.starter.module.category.service.CategoryService;
import id.or.greenlabs.vertx.starter.module.category.usecase.Find;
import id.or.greenlabs.vertx.starter.module.category.usecase.Save;
import id.or.greenlabs.vertx.starter.module.category.usecase.impl.FindImpl;
import id.or.greenlabs.vertx.starter.module.category.usecase.impl.SaveImpl;

/**
 * @author krissadewo
 * @date 1/21/22 1:17 PM
 */
public class CategoryModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(CategoryRepository.class).to(CategoryRepositoryImpl.class);
        bind(CategoryAdapter.class).to(CategoryService.class);
        bind(Find.class).to(FindImpl.class);
        bind(Save.class).to(SaveImpl.class);
    }
}
