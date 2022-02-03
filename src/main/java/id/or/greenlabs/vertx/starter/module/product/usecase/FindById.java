package id.or.greenlabs.vertx.starter.module.product.usecase;

import id.or.greenlabs.vertx.starter.assembler.dto.ProductDto;
import reactor.core.publisher.Mono;

/**
 * @author krissadewo
 * @date 1/31/22 2:48 PM
 */
public interface FindById {

    Mono<ProductDto> execute(String id);
}
