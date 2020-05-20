package iot.technology.coap;

import org.eclipse.californium.core.CoapResource;
import org.eclipse.californium.core.server.resources.CoapExchange;

import static org.eclipse.californium.core.coap.CoAP.ResponseCode.BAD_REQUEST;
import static org.eclipse.californium.core.coap.CoAP.ResponseCode.CHANGED;

/**
 * @author james mu
 * @date 2020/5/9 09:38
 */
public class WritableResource extends CoapResource {

    public String value = "to be replaced";

    public WritableResource() {
        super("write");
    }

    @Override
    public void handleGET(CoapExchange exchange) {
        exchange.respond(value);
    }

    @Override
    public void handlePUT(CoapExchange exchange) {
        byte[] payload = exchange.getRequestPayload();

        try {
            value = new String(payload, "UTF-8");
            exchange.respond(CHANGED, value);
        } catch (Exception e) {
            e.printStackTrace();
            exchange.respond(BAD_REQUEST, "Invalid String");
        }
    }
}
