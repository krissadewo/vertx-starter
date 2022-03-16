package id.or.greenlabs.vertx.starter.api;

import io.vertx.core.json.Json;
import io.vertx.ext.web.RoutingContext;
import reactor.core.publisher.Mono;

/**
 * @author krissadewo
 * @date 2/9/22 10:02 AM
 */
public interface BaseApi {

    default Mono<Object> doSuccessResponse(RoutingContext context, Object object) {
        return Mono.just(
            context.response()
                .setStatusCode(200)
                .putHeader("content-type", "application/json")
                .end(Json.encode(object))
        );
    }

    default Mono<Object> doFailedResponse(RoutingContext context, Object object) {
        return Mono.just(
            context.response()
                .setStatusCode(500)
                .putHeader("content-type", "application/json")
                .end(Json.encode(object))
        );
    }
}
