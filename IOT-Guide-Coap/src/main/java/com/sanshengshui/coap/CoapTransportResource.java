package com.sanshengshui.coap;

import com.sanshengshui.coap.common.FeatureType;
import com.sanshengshui.coap.session.SessionMsgType;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.californium.core.CoapResource;
import org.eclipse.californium.core.coap.CoAP;
import org.eclipse.californium.core.coap.Request;
import org.eclipse.californium.core.network.Exchange;
import org.eclipse.californium.core.server.resources.CoapExchange;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Optional;

@Slf4j
public class CoapTransportResource extends CoapResource {

    public static final int FEATURE_TYPE_POSITION = 4;

    private final Field observerField;
    private final long timeout;

    public CoapTransportResource(String name,long timeout) {
        super(name);
        this.timeout = timeout;
        this.setObservable(false);
        observerField = ReflectionUtils.findField(Exchange.class,"observer");
        observerField.setAccessible(true);
    }

    @Override
    public void handleGET(CoapExchange exchange) {
        Optional<FeatureType> featureType = getFeatureType(exchange.advanced().getRequest());
        if (!featureType.isPresent()) {
            log.trace("Missing feature type parameter");
        } else if (featureType.get() == FeatureType.TELEMETRY) {
            log.trace("Can't fetch/subscribe to timeseries updates");
            exchange.respond(CoAP.ResponseCode.BAD_REQUEST);
        } else if (exchange.getRequestOptions().hasObserve()) {
            processExchangeGetRequest(exchange, featureType.get());
        } else if (featureType.get() == FeatureType.ATTRIBUTES) {
            processRequest(exchange, SessionMsgType.GET_ATTRIBUTES_REQUEST);
        } else {
            log.trace("Invalid feature type parameter");
            exchange.respond(CoAP.ResponseCode.BAD_REQUEST);
        }
    }

    private void processExchangeGetRequest(CoapExchange exchange, FeatureType featureType) {
        boolean unsubscribe = exchange.getRequestOptions().getObserve() == 1;
        SessionMsgType sessionMsgType;
        sessionMsgType = unsubscribe ? SessionMsgType.UNSUBSCRIBE_ATTRIBUTES_REQUEST : SessionMsgType.SUBSCRIBE_ATTRIBUTES_REQUEST;
        processRequest(exchange, sessionMsgType);
    }



    @Override
    public void handlePOST(CoapExchange exchange) {
        Optional<FeatureType> featureType = getFeatureType(exchange.advanced().getRequest());
        if (!featureType.isPresent()) {
            log.trace("Missing feature type parameter");
            exchange.respond(CoAP.ResponseCode.BAD_REQUEST);
        } else {
            switch (featureType.get()) {
                case ATTRIBUTES:
                    processRequest(exchange, SessionMsgType.POST_ATTRIBUTES_REQUEST);
                    break;
                case TELEMETRY:
                    processRequest(exchange, SessionMsgType.POST_TELEMETRY_REQUEST);
                    break;
            }
        }
    }

    private void processRequest(CoapExchange exchange, SessionMsgType type) {
        log.trace("Processing {}", exchange.advanced().getRequest());
        exchange.accept();
        Exchange advanced = exchange.advanced();
        Request request = advanced.getRequest();

        try {
            switch (type) {
                case GET_ATTRIBUTES_REQUEST:
                case POST_TELEMETRY_REQUEST:
                case POST_ATTRIBUTES_REQUEST:
                    break;
                case SUBSCRIBE_ATTRIBUTES_REQUEST:
                    break;
                case UNSUBSCRIBE_ATTRIBUTES_REQUEST:
                    break;
                default:
                    log.trace("Unsupported msg type: {}", type);
                    throw new IllegalArgumentException("Unsupported msg type: " + type);
            }
        } catch (IllegalArgumentException  e) {
            log.debug("Failed to process payload {}", e);
            exchange.respond(CoAP.ResponseCode.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }


    private Optional<FeatureType> getFeatureType(Request request) {
        List<String> uriPath = request.getOptions().getUriPath();
        try {
            if (uriPath.size() >= FEATURE_TYPE_POSITION) {
                return Optional.of(FeatureType.valueOf(uriPath.get(FEATURE_TYPE_POSITION - 1).toUpperCase()));
            }
        } catch (RuntimeException e) {
            log.warn("Failed to decode feature type: {}", uriPath);
        }
        return Optional.empty();
    }
}
