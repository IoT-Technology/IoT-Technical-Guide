import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @Author: 穆书伟
 * @Date: 19-4-2 下午3:45
 * @Version 1.0
 */
@SpringBootApplication(scanBasePackages = {"com.sanshengshui.http"})
public class IOTHttpServer {
    /**
     * @param args
     */
    public static void main(String[] args) {
        SpringApplication.run(IOTHttpServer.class);
    }
}
