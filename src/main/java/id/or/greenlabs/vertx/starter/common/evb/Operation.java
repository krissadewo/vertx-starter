package id.or.greenlabs.vertx.starter.common.evb;

import lombok.Data;

import java.util.Map;

/**
 * @author krissadewo
 * @date 2/4/22 10:26 AM
 */
@Data
public class Operation {

    String command;

    Map<String, Object> param;

    Object body;

    /**
     * @param command see {@link EvbCommand}
     */
    public Operation(String command) {
        this.command = command;
    }

    /**
     * @param command command see {@link EvbCommand}
     * @param param   can fill anytype of param
     */
    public Operation(String command, Map<String, Object> param) {
        this.command = command;
        this.param = param;
    }

    public Operation(String command, Object body) {
        this.command = command;
        this.body = body;
    }
}
