package iot.technology.coap;

import org.eclipse.californium.core.CoapResource;
import org.eclipse.californium.core.server.resources.CoapExchange;

import static org.eclipse.californium.core.coap.CoAP.ResponseCode.DELETED;

/**
 * @author james mu
 * @date 2020/5/9 09:38
 */
public class RemovableResource extends CoapResource {

    public RemovableResource() {
        super("remove");
    }

    @Override
    public void handleDELETE(CoapExchange exchange) {
        delete();
        exchange.respond(DELETED);
    }
}
