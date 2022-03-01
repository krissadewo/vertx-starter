package id.or.greenlabs.vertx.starter.module.category.service;

import id.or.greenlabs.vertx.starter.assembler.dto.CategoryDto;
import id.or.greenlabs.vertx.starter.assembler.wrapper.CategoryWrapper;
import id.or.greenlabs.vertx.starter.module.category.port.CategoryAdapter;
import id.or.greenlabs.vertx.starter.module.category.repository.CategoryRepository;
import id.or.greenlabs.vertx.starter.service.ApplicationService;
import io.vertx.core.Vertx;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.inject.Inject;
import javax.inject.Named;

/**
 * @author krissadewo
 * @date 2/9/22 10:31 AM
 */
public class CategoryService extends ApplicationService implements CategoryAdapter {

    private CategoryRepository categoryRepository;

    @Inject
    public CategoryService(@Named("vertx") Vertx vertx, CategoryRepository categoryRepository) {
        super(vertx);

        this.categoryRepository = categoryRepository;
    }

    @Override
    public Mono<CategoryDto> save(CategoryDto categoryDto) {
        return categoryRepository.save(new CategoryWrapper().toDocument(categoryDto))
            .map(category -> {
                return new CategoryWrapper().toDto(category);
            });
    }

    @Override
    public Mono<CategoryDto> update(CategoryDto categoryDto) {
        return null;
    }

    @Override
    public Mono<CategoryDto> delete(String id) {
        return null;
    }

    @Override
    public Flux<CategoryDto> findBy(CategoryDto param, int offset, int limit) {
        return categoryRepository.findBy(new CategoryWrapper().toParam(param), offset, limit)
            .map(category -> {
                return new CategoryWrapper().toDto(category);
            });
    }
}
