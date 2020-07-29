package iot.technology.gateway.opcua.client;

import iot.technology.gateway.opcua.config.Properties;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.milo.opcua.sdk.client.OpcUaClient;
import org.eclipse.milo.opcua.sdk.client.api.config.OpcUaClientConfig;
import org.eclipse.milo.opcua.sdk.client.api.identity.AnonymousProvider;
import org.eclipse.milo.opcua.stack.client.DiscoveryClient;
import org.eclipse.milo.opcua.stack.core.Stack;
import org.eclipse.milo.opcua.stack.core.security.SecurityPolicy;
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.Unsigned;
import org.eclipse.milo.opcua.stack.core.types.structured.EndpointDescription;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.function.Predicate;

/**
 * @author james mu
 * @date 2020/7/9 23:25
 */
@Slf4j
@Component
public class ClientRunner {

    private final CompletableFuture<OpcUaClient> future = new CompletableFuture<>();

    private final Properties properties;

    @Autowired
    public ClientRunner(Properties properties) {
        this.properties = properties;
    }


    public OpcUaClient run() throws Exception {
        OpcUaClient client = createClient();
        future.whenCompleteAsync((c, ex) -> {
            if (Objects.nonNull(ex)) {
                log.error("Error running example: {}", ex.getMessage(), ex);
            }

            try {
                c.disconnect().get();
                Stack.releaseSharedResources();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                log.error("Error disconnecting: ", e.getMessage(), e);
            }
        });
        return client;
    }

    private OpcUaClient createClient() throws Exception {

        // 搜索OPC节点
        List<EndpointDescription> endpoints = null;
        try {
            endpoints = DiscoveryClient.getEndpoints(properties.getEndpointUrl()).get();
        } catch (Throwable e) {
            // try the explicit discovery endpoint as well
            String discoveryUrl = properties.getEndpointUrl();

            if (!discoveryUrl.endsWith("/")) {
                discoveryUrl += "/";
            }
            discoveryUrl += "discovery";

            log.info("Trying explicit discovery URL: {}", discoveryUrl);
            endpoints = DiscoveryClient.getEndpoints(discoveryUrl).get();
        }

        EndpointDescription endpoint = endpoints.stream()
                .filter(e -> e.getSecurityPolicyUri().equals(SecurityPolicy.None.getUri())).filter(endpointFilter())
                .findFirst().orElseThrow(() -> new Exception("no desired endpoints returned"));

        OpcUaClientConfig config = OpcUaClientConfig.builder()
                .setApplicationName(LocalizedText.english(properties.getAppName()))
                /**
                 * 匿名验证
                 */
                .setEndpoint(endpoint).setIdentityProvider(new AnonymousProvider())
                .setRequestTimeout(Unsigned.uint(5000)).build();

        return OpcUaClient.create(config);
    }

    /**
     * @MethodName: endpointFilter
     * @Description: endpointFilter
     * @return
     */
    private Predicate<EndpointDescription> endpointFilter() {
        return e -> true;
    }

    /**
     * @return the future
     */
    public CompletableFuture<OpcUaClient> getFuture() {
        return future;
    }
}
