package id.or.greenlabs.vertx.starter.module.category.usecase;

import id.or.greenlabs.vertx.starter.assembler.dto.CategoryDto;
import reactor.core.publisher.Flux;

/**
 * @author krissadewo
 * @date 2/9/22 10:43 AM
 */
public interface Find {

    Flux<CategoryDto> execute(CategoryDto param, int limit, int offset);
}
