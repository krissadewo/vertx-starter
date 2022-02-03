package id.or.greenlabs.vertx.starter.common.subscribe;

import java.util.ArrayList;

/**
 * @author krissadewo
 * @date 5/3/21 7:52 PM
 */
public abstract class SingleSubscriber<T> extends AbstractSubscriber<T> {

    public SingleSubscriber() {
        super.errors = new ArrayList<>();
        super.received = new ArrayList<>();
    }
}
