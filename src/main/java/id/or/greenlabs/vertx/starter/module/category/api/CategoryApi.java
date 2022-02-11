package id.or.greenlabs.vertx.starter.module.category.api;

import id.or.greenlabs.vertx.starter.api.BaseApi;
import id.or.greenlabs.vertx.starter.api.response.HttpResponse;
import id.or.greenlabs.vertx.starter.assembler.dto.CategoryDto;
import id.or.greenlabs.vertx.starter.common.DefaultException;
import id.or.greenlabs.vertx.starter.common.StatusCode;
import id.or.greenlabs.vertx.starter.module.category.usecase.Find;
import id.or.greenlabs.vertx.starter.module.category.usecase.Save;
import io.vertx.core.Vertx;
import io.vertx.core.json.Json;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.handler.BodyHandler;
import io.vertx.ext.web.validation.RequestParameters;
import io.vertx.ext.web.validation.ValidationHandler;
import io.vertx.ext.web.validation.builder.Parameters;
import io.vertx.json.schema.SchemaParser;
import io.vertx.json.schema.SchemaRouter;
import io.vertx.json.schema.SchemaRouterOptions;
import io.vertx.json.schema.common.dsl.Schemas;
import reactor.core.publisher.Mono;

import javax.inject.Inject;
import javax.inject.Named;

/**
 * @author krissadewo
 * @date 2/9/22 10:27 AM
 */
public class CategoryApi implements BaseApi {

    @Inject
    private Save save;

    @Inject
    private Find find;

    @Inject
    public CategoryApi(@Named("router") Router router) {
        router.route().handler(BodyHandler.create());
        router.post("/categories").handler(this::save);

        router.get("/categories")
            .handler(
                ValidationHandler
                    .builder(SchemaParser.createOpenAPI3SchemaParser(SchemaRouter.create(Vertx.vertx(), new SchemaRouterOptions())))
                    .queryParameter(Parameters.optionalParam("name", Schemas.stringSchema()))
                    .queryParameter(Parameters.param("limit", Schemas.intSchema()))
                    .queryParameter(Parameters.param("offset", Schemas.intSchema()))
                    .build()
            )
            .handler(this::find);
    }

    private void save(RoutingContext context) {
        save.execute(Json.decodeValue(context.getBodyAsString(), CategoryDto.class))
            .switchIfEmpty(Mono.error(new DefaultException(StatusCode.OPERATION_FAILED)))
            .onErrorReturn(new CategoryDto())
            .flatMap(result -> {
                if (result.getId() != null) {
                    HttpResponse.Single single = HttpResponse.Single.builder()
                        .status(StatusCode.OPERATION_SUCCESS)
                        .build();

                    return doSuccessResponse(context, single);
                }

                HttpResponse.Single single = HttpResponse.Single.builder()
                    .status(StatusCode.OPERATION_FAILED)
                    .build();

                return doFailedResponse(context, single);
            })
            .subscribe();
    }

    private void find(RoutingContext context) {
        RequestParameters parameters = context.get(ValidationHandler.REQUEST_CONTEXT_KEY);

        int limit = parameters.queryParameter("limit").getInteger();
        int offset = parameters.queryParameter("offset").getInteger();
        String name = parameters.queryParameter("name") == null ? null : parameters.queryParameter("name").getString();

        CategoryDto param = new CategoryDto();
        param.setName(name);

        find.execute(param, limit, offset)
            .switchIfEmpty(Mono.error(new DefaultException(StatusCode.DATA_NOT_FOUND)))
            .flatMap(result -> {
                HttpResponse.Many many = HttpResponse.Many.builder()
                    .data(result)
                    .status(StatusCode.OPERATION_SUCCESS)
                    .build();

                return doSuccessResponse(context, many);
            })
            .subscribe();
    }

}
