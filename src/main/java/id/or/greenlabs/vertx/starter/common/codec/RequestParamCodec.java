package id.or.greenlabs.vertx.starter.common.codec;

import id.or.greenlabs.vertx.starter.common.evb.Operation;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.eventbus.MessageCodec;

/**
 * @author krissadewo
 * @date 2/4/22 3:18 PM
 */
public class RequestParamCodec implements MessageCodec<Operation, Operation> {

    @Override
    public void encodeToWire(Buffer buffer, Operation operation) {
    }

    @Override
    public Operation decodeFromWire(int pos, Buffer buffer) {
        return null;
    }

    @Override
    public Operation transform(Operation operation) {
        return operation;
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
