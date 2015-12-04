package vertxapp;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.http.HttpServer;
import io.vertx.core.http.HttpServerOptions;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.handler.LoggerHandler;
import io.vertx.ext.web.handler.StaticHandler;

/**
 * @author Bruno Salmon
 */
public class WebVerticle extends AbstractVerticle {

    // Convenient method to run the microservice directly in the IDE
    public static void main(String[] args) {
        VertxRunner.runVerticle(WebVerticle.class);
    }

    @Override
    public void start() throws Exception {
        // Creating web server and its router
        HttpServerOptions httpServerOptions = new HttpServerOptions()
                .setCompressionSupported(true) // enabling gzip and deflate compression
                .setPort(80); // web port
        HttpServer server = vertx.createHttpServer(httpServerOptions);
        Router router = Router.router(vertx);

        // Logging web requests
        router.route("/*").handler(LoggerHandler.create());

        // Serving static files under the webroot folder
        router.route("/*").handler(StaticHandler.create());

        // Binding the web port
        server.requestHandler(router::accept).listen();
    }
}
