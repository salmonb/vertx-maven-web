package vertxapp;

import io.vertx.core.AbstractVerticle;

/**
 * @author Bruno Salmon
 */
public class PrimaryVerticle extends AbstractVerticle {

    // Convenient method to run the microservice directly in the IDE
    public static void main(String[] args) {
        VertxRunner.runVerticle(PrimaryVerticle.class);
    }


    @Override
    public void start() throws Exception {
        // Deploying the web server
        vertx.deployVerticle(new WebVerticle(), event1 -> System.out.println("Vertx-web is running. Please check http://localhost"));

        // Deploying the web app
        vertx.deployVerticle("maven:salmonb:web-app:1.0-SNAPSHOT::web-app", event -> {
            if (event.succeeded())
                System.out.println("Maven web-app successfully deployed. Please check http://localhost/webapp/ to see if it is served");
            else
                System.out.println("Error when deploying webapp: " + event.cause());
        });
    }

}
