package iot.technology.http;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @Author: 穆书伟
 * @Date: 19-4-17 上午9:17
 * @Version 1.0
 */
@SpringBootApplication(scanBasePackages = {"iot.technology.http"})
public class HttpApiServer {

    /**
     * @param args
     */
    public static void main(String[] args) {
        SpringApplication.run(HttpApiServer.class);
    }
}
