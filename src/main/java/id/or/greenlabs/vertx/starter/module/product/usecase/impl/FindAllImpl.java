package id.or.greenlabs.vertx.starter.module.product.usecase.impl;

import id.or.greenlabs.vertx.starter.assembler.dto.ProductDto;
import id.or.greenlabs.vertx.starter.document.Product;
import id.or.greenlabs.vertx.starter.module.product.port.ProductAdapter;
import id.or.greenlabs.vertx.starter.module.product.usecase.FindAll;
import reactor.core.publisher.Flux;

import javax.inject.Inject;

/**
 * @author krissadewo
 * @date 1/31/22 3:04 PM
 */
public class FindAllImpl implements FindAll {

    @Inject
    private ProductAdapter adapter;

    @Override
    public Flux<Product> execute(ProductDto param, int limit, int offset) {
        return adapter.find(param, limit, offset);
    }
}
