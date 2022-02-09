package id.or.greenlabs.vertx.starter.module.category.usecase.impl;

import id.or.greenlabs.vertx.starter.assembler.dto.CategoryDto;
import id.or.greenlabs.vertx.starter.module.category.port.CategoryAdapter;
import id.or.greenlabs.vertx.starter.module.category.usecase.Save;
import reactor.core.publisher.Mono;

import javax.inject.Inject;

/**
 * @author krissadewo
 * @date 2/9/22 10:42 AM
 */
public class SaveImpl implements Save {

    @Inject
    private CategoryAdapter adapter;

    @Override
    public Mono<CategoryDto> execute(CategoryDto dto) {
        return adapter.save(dto);
    }
}
