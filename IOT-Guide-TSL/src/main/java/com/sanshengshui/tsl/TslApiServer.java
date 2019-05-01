package com.sanshengshui.tsl;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @Author: 穆书伟
 * @Date: 19-4-16
 * @Version 1.0
 */
@SpringBootApplication(scanBasePackages = {"com.sanshengshui.tsl"})
public class TslApiServer {

    /**
     * @param args
     */
    public static void main(String[] args) {
        SpringApplication.run(TslApiServer.class);
    }
}
