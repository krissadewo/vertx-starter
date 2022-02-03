package id.or.greenlabs.vertx.starter.common.subscribe;

import java.util.ArrayList;

/**
 * @author krissadewo
 * @date 5/3/21 7:52 PM
 */
public abstract class ManySubscriber<T> extends AbstractSubscriber<T> {

    public ManySubscriber() {
        super.received = new ArrayList<>();
        super.errors = new ArrayList<>();
    }

    @Override
    public void onSuccess(T result) {
    }

}
