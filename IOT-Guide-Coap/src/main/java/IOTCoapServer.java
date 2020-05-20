
import iot.technology.coap.*;
import org.eclipse.californium.core.CoapResource;
import org.eclipse.californium.core.CoapServer;
import java.net.UnknownHostException;

/**
 * @Author: james mu
 * @Date: 19-4-2 上午11:06
 * @Version 1.0
 */
public class IOTCoapServer {

    public static void main(String[] args) throws UnknownHostException {

        CoapServer server = new CoapServer();


        server.add(new HelloResource());

        CoapResource path = new CoapResource("subpath");
        path.add(new AnotherResource());
        server.add(path);

        server.add(new RemovableResource(), new TimeResource(), new WritableResource());


        server.start();
    }

}
