package id.or.greenlabs.vertx.starter.api.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

/**
 * @author krissadewo
 * @date 2/9/22 10:02 AM
 */
public class HttpResponse {

    @Data
    @Builder
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class Many {

        private Object data;

        private String status;

        private Long rows;

    }

    @Data
    @Builder
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class Single {

        private Object data;

        private String status;
    }
}
