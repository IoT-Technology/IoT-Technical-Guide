package iot.technology.realtime;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author james mu
 * @date 2020/5/20 17:08
 */
@SpringBootApplication(scanBasePackages = {"iot.technology.realtime"})
public class RealTimeServer {

    public static void main(String[] args) {
        SpringApplication.run(RealTimeServer.class);
    }
}
