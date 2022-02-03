package id.or.greenlabs.vertx.starter.module.product.usecase;

import id.or.greenlabs.vertx.starter.assembler.dto.ProductDto;
import reactor.core.publisher.Flux;

/**
 * @author krissadewo
 * @date 1/31/22 3:03 PM
 */
public interface Find {

    Flux<ProductDto> execute(ProductDto param, int limit, int offset);
}
