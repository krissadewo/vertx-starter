package id.or.greenlabs.vertx.starter.module.category.port;

import id.or.greenlabs.vertx.starter.assembler.dto.CategoryDto;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * @author krissadewo
 * @date 2/9/22 10:28 AM
 */
public interface CategoryAdapter {

    Mono<CategoryDto> save(CategoryDto categoryDto);

    Mono<CategoryDto> update(CategoryDto categoryDto);

    Mono<CategoryDto> delete(String id);

    Flux<CategoryDto> find(CategoryDto param, int offset, int limit);
}
