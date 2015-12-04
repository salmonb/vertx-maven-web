package dummy;

import io.vertx.core.AbstractVerticle;

/**
 * @author Bruno Salmon
 */
public class DummyVerticle extends AbstractVerticle {

    @Override
    public void start() throws Exception {
        System.out.println("Web-app DummyVerticle started");
    }
}
