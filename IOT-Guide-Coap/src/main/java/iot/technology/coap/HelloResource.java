package iot.technology.coap;

import org.eclipse.californium.core.CoapResource;
import org.eclipse.californium.core.server.resources.CoapExchange;

/**
 * @author james mu
 * @date 2020/5/9 09:33
 */
public class HelloResource extends CoapResource {

    public HelloResource() {
        super("hello");

        getAttributes().setTitle("Hello-World Resource");
    }

    @Override
    public void handleGET(CoapExchange exchange) {
        exchange.respond("Hello world!");
    }
}
