package id.or.greenlabs.vertx.starter.common.subscribe;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * @author krissadewo
 * @date 5/3/21 7:46 PM
 */
abstract class AbstractSubscriber<T> implements Subscriber<T> {

    protected List<T> received;

    protected List<Throwable> errors;

    protected CountDownLatch latch = new CountDownLatch(1);

    private volatile Subscription subscription;

    private volatile boolean completed;

    @Override
    public void onSubscribe(final Subscription subscription) {
        this.subscription = subscription;

        subscription.request(Integer.MAX_VALUE);
    }

    @Override
    public void onNext(final T t) {
        received.add(t);

        onSuccess(t);
    }

    public abstract void onSuccess(final T result);

    public abstract void onFailure(Throwable throwable);

    @Override
    public void onError(Throwable throwable) {
        errors.add(throwable);

        onFailure(throwable);
        onComplete();
    }

    @Override
    public void onComplete() {
        completed = true;

        latch.countDown();

        if (received.isEmpty()) {
            onSuccess(null);
        }
    }

    public Subscription getSubscription() {
        return subscription;
    }

    public List<T> getReceived() {
        return received;
    }

    public Throwable getError() {
        if (errors.size() > 0) {
            return errors.get(0);
        }

        return null;
    }

    public boolean isCompleted() {
        return completed;
    }

    public List<T> get(final long timeout, final TimeUnit unit) throws Throwable {
        return await(timeout, unit).getReceived();
    }

    public AbstractSubscriber<T> await() throws Throwable {
        return await(Long.MAX_VALUE, TimeUnit.MILLISECONDS);
    }

    public AbstractSubscriber<T> await(final long timeout, final TimeUnit unit) throws Throwable {
        subscription.request(Integer.MAX_VALUE);

        if (!latch.await(timeout, unit)) {
            throw new RuntimeException("Publisher onComplete timed out");
        }

        if (!errors.isEmpty()) {
            throw errors.get(0);
        }

        return this;
    }
}
