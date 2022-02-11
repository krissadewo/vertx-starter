package id.or.greenlabs.vertx.starter;

import io.vertx.core.Vertx;
import io.vertx.ext.web.client.WebClientOptions;
import io.vertx.junit5.VertxExtension;
import io.vertx.junit5.VertxTestContext;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;

/**
 * @author krissadewo
 * @date 2/11/22 9:31 AM
 */
@ExtendWith(VertxExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public abstract class AbstractApiTest extends AbstractGenericTest {

    protected WebClientOptions opts = new WebClientOptions()
        .setDefaultPort(8080)
        .setDefaultHost("localhost");

    protected void initInjector(VertxTestContext context) {
    }

    protected abstract void prepareBuilder(Vertx vertx, VertxTestContext testContext);

}
