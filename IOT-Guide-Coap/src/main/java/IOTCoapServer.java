import com.sanshengshui.coap.CoapTransportResource;
import org.eclipse.californium.core.CoapResource;
import org.eclipse.californium.core.CoapServer;
import org.eclipse.californium.core.network.CoapEndpoint;

import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.UnknownHostException;

/**
 * @Author: 穆书伟
 * @Date: 19-4-2 上午11:06
 * @Version 1.0
 */
public class IOTCoapServer {

    private static final String V1 = "v1";
    private static final String API = "api";


    private static String host = "127.0.0.1";
    private static Integer port = 5683;
    private static long timeout = 10000;

    public static void main(String[] args) throws UnknownHostException {
        CoapServer coapServer = new CoapServer();
        CoapResource api = new CoapResource(API);
        api.add(new CoapTransportResource(V1,timeout));
        coapServer.add(api);
        InetAddress addr = InetAddress.getByName(host);
        InetSocketAddress sockAddr = new InetSocketAddress(addr, port);
        coapServer.addEndpoint(new CoapEndpoint(sockAddr));
        coapServer.start();

    }

}
