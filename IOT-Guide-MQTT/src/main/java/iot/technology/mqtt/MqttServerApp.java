package iot.technology.mqtt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author jamesmsw
 * @date 2021/3/9 8:41 下午
 */
@SpringBootApplication(scanBasePackages = "iot.technology.mqtt")
public class MqttServerApp {

    /**
     * @param args
     */
    public static void main(String[] args) {
        SpringApplication.run(MqttServerApp.class, args);
    }
}
