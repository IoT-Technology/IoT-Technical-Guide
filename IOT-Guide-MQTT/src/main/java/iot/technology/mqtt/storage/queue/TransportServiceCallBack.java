package iot.technology.mqtt.storage.queue;

/**
 * @author james mu
 * @date 2020/8/31 12:22
 */
public interface TransportServiceCallBack<T> {

    TransportServiceCallBack<Void> EMPTY = new TransportServiceCallBack<Void>() {
        @Override
        public void onSuccess(Void msg) {

        }

        @Override
        public void onError(Throwable e) {

        }
    };

    void onSuccess(T msg);

    void onError(Throwable e);
}
