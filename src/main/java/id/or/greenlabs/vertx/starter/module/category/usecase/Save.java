package id.or.greenlabs.vertx.starter.module.category.usecase;

import id.or.greenlabs.vertx.starter.assembler.dto.CategoryDto;
import reactor.core.publisher.Mono;

/**
 * @author krissadewo
 * @date 2/9/22 10:41 AM
 */
public interface Save {

    Mono<CategoryDto> execute(CategoryDto dto);
}
