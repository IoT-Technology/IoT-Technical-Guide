package iot.technology.dao.sql;

import com.google.common.util.concurrent.ListeningExecutorService;
import com.google.common.util.concurrent.MoreExecutors;

import javax.annotation.PreDestroy;
import java.util.concurrent.Executors;

public abstract class JpaAbstractDaoListeningExecutorService {

    protected ListeningExecutorService service = MoreExecutors.listeningDecorator(Executors.newFixedThreadPool(10));

    @PreDestroy
    void onDestroy() {
        service.shutdown();
    }
}
