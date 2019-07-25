package com.sanshengshui.coap;

import com.sanshengshui.coap.adaptors.JsonCoapAdaptor;
import com.sanshengshui.coap.common.FeatureType;
import com.sanshengshui.coap.session.SessionMsgType;
import com.sanshengshui.tsl.adaptor.AdaptorException;
import org.eclipse.californium.core.CoapResource;
import org.eclipse.californium.core.coap.CoAP;
import org.eclipse.californium.core.coap.Request;
import org.eclipse.californium.core.network.Exchange;
import org.eclipse.californium.core.server.resources.CoapExchange;
import org.eclipse.californium.core.server.resources.Resource;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Optional;

public class CoapTransportResource extends CoapResource {

    public static final int FEATURE_TYPE_POSITION = 3;

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
        } else if (featureType.get() == FeatureType.TELEMETRY) {
            exchange.respond(CoAP.ResponseCode.BAD_REQUEST);
        }  else if (featureType.get() == FeatureType.ATTRIBUTES) {
            processRequest(exchange, SessionMsgType.GET_ATTRIBUTES_REQUEST);
        } else {
            exchange.respond(CoAP.ResponseCode.BAD_REQUEST);
        }
    }




    @Override
    public void handlePOST(CoapExchange exchange) {
        Optional<FeatureType> featureType = getFeatureType(exchange.advanced().getRequest());
        if (!featureType.isPresent()) {
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
        exchange.accept();
        Exchange advanced = exchange.advanced();
        Request request = advanced.getRequest();

        try {
            switch (type) {
                case GET_ATTRIBUTES_REQUEST:
                case POST_TELEMETRY_REQUEST:
                case POST_ATTRIBUTES_REQUEST:
                    JsonCoapAdaptor.convertToMsg(type,request);
                    break;
                default:
                    throw new IllegalArgumentException("Unsupported msg type: " + type);
            }
            exchange.respond("Data has been received");
        } catch (AdaptorException e){
            exchange.respond(CoAP.ResponseCode.BAD_REQUEST, e.getMessage());
        } catch (IllegalArgumentException  e) {
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
        }
        return Optional.empty();
    }

    @Override
    public Resource getChild(String name) {
        return this;
    }


}
