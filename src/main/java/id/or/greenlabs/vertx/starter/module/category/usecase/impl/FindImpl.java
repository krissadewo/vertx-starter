package id.or.greenlabs.vertx.starter.module.category.usecase.impl;

import id.or.greenlabs.vertx.starter.assembler.dto.CategoryDto;
import id.or.greenlabs.vertx.starter.module.category.port.CategoryAdapter;
import id.or.greenlabs.vertx.starter.module.category.usecase.Find;
import reactor.core.publisher.Flux;

import javax.inject.Inject;

/**
 * @author krissadewo
 * @date 2/9/22 10:43 AM
 */
public class FindImpl implements Find {

    @Inject
    public CategoryAdapter adapter;

    @Override
    public Flux<CategoryDto> execute(CategoryDto param, int limit, int offset) {
        return adapter.findBy(param, limit, offset);
    }
}
