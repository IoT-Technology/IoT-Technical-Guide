package iot.technology.coap;

import org.eclipse.californium.core.CoapResource;
import org.eclipse.californium.core.server.resources.CoapExchange;

/**
 * @author james mu
 * @date 2020/5/9 09:42
 */
public class TimeResource extends CoapResource {

    public TimeResource() {
        super("time");
    }

    @Override
    public void handleGET(CoapExchange exchange) {
        exchange.respond(String.valueOf(System.currentTimeMillis()));
    }
}
