package id.or.greenlabs.vertx.starter.module.category.api;

import id.or.greenlabs.vertx.starter.api.BaseApi;
import id.or.greenlabs.vertx.starter.module.category.usecase.Save;

import javax.inject.Inject;

/**
 * @author krissadewo
 * @date 2/9/22 10:27 AM
 */
public class CategoryApi implements BaseApi {

    @Inject
    public Save save;
}
