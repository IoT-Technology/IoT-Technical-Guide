package iot.technology.coap;

import org.eclipse.californium.core.CoapResource;
import org.eclipse.californium.core.server.resources.CoapExchange;

/**
 * @author james mu
 * @date 2020/5/9 09:35
 */
public class AnotherResource extends CoapResource {

    public AnotherResource() {
        //资源标志符
        super("Another");
        //设置显示名称
        getAttributes().setTitle("Another Hello-World Resource");
    }

    @Override
    public void handleGET(CoapExchange exchange) {
        exchange.respond("Fun with CoAP!");
    }
}
