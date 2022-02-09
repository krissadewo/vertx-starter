package id.or.greenlabs.vertx.starter.common.codec;

import io.vertx.core.buffer.Buffer;
import io.vertx.core.eventbus.MessageCodec;

/**
 * @author krissadewo
 * @date 2/4/22 10:36 AM
 */
public class GenericCodec implements MessageCodec<Object, Object> {

    @Override
    public void encodeToWire(Buffer buffer, Object objects) {
    }

    @Override
    public Object decodeFromWire(int pos, Buffer buffer) {
        return null;
    }

    @Override
    public Object transform(Object object) {
        return object;
    }

    @Override
    public String name() {
        return this.getClass().getSimpleName();
    }

    @Override
    public byte systemCodecID() {
        return -1;
    }
}
