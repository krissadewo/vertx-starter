package id.or.greenlabs.vertx.starter.module.product.usecase.impl;

import id.or.greenlabs.vertx.starter.assembler.dto.ProductDto;
import id.or.greenlabs.vertx.starter.module.product.port.ProductAdapter;
import id.or.greenlabs.vertx.starter.module.product.usecase.FindById;
import reactor.core.publisher.Mono;

import javax.inject.Inject;

/**
 * @author krissadewo
 * @date 1/31/22 2:49 PM
 */
public class FindByIdImpl implements FindById {

    @Inject
    private ProductAdapter adapter;

    @Override
    public Mono<ProductDto> execute(String id) {
        return adapter.findBy(id);
    }
}
