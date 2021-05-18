package iot.technology.mqtt.storage;

import iot.technology.mqtt.storage.msg.ProtoQueueMsg;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author jamesmsw
 * @date 2021/2/19 9:24 上午
 */
@Slf4j
@Service
public class DefaultTransportService {

    private ExecutorService mainConsumerExecutor = Executors.newSingleThreadExecutor();
    private volatile boolean stopped = false;
    protected Consumer<ProtoQueueMsg> transportConsumer;

    public DefaultTransportService() {
    }

    @PostConstruct
    public void init() {
        transportConsumer = new Consumer<>("transport.notifications");
        mainConsumerExecutor.execute(() -> {
            while (!stopped) {
                try {
                    List<ProtoQueueMsg> records =  transportConsumer.poll(25);
                    if (records.size() == 0) {
                        continue;
                    }
                    records.forEach(record -> {
                        try {
                            //processToTransportMsg(record.getValue());
                        } catch (Throwable e) {
                            log.warn("Failed to process the notification.", e);
                        }
                    });
                    transportConsumer.commit();
                } catch (Exception e) {
                    if (!stopped) {
                        log.warn("Failed to obtain messages from queue.", e);
                        try {
                            Thread.sleep(25);
                        } catch (InterruptedException e2) {
                            log.trace("Failed to wait until the server has capacity to handle new requests", e2);
                        }
                    }
                }
            }

        });
    }

    @PreDestroy
    public void destroy() {
        stopped = true;
        if (transportConsumer != null) {
            transportConsumer.unsubscribe();
        }
    }

    protected void processToTransportMsg(byte[] value) {

    }

}
