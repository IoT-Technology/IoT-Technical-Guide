package com.sanshengshui.coap.session;

public enum SessionMsgType {
    SUBSCRIBE_ATTRIBUTES_REQUEST,
    UNSUBSCRIBE_ATTRIBUTES_REQUEST,
    GET_ATTRIBUTES_REQUEST(),
    POST_ATTRIBUTES_REQUEST(),
    POST_TELEMETRY_REQUEST();



    SessionMsgType() {
    }

}
