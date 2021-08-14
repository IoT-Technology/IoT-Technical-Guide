package iot.technology.actor;

import lombok.Getter;
import lombok.ToString;

/**
 * @author mushuwei
 */
@ToString
public class InitFailureStrategy {
    @Getter
    private boolean stop;
    @Getter
    private long retryDelay;

    private InitFailureStrategy(boolean stop, long retryDelay) {
        this.stop = stop;
        this.retryDelay = retryDelay;
    }

    public static InitFailureStrategy retryImmediately() {
        return new InitFailureStrategy(false, 0);
    }

    public static InitFailureStrategy retryWithDelay(long ms) {
        return new InitFailureStrategy(false, ms);
    }

    public static InitFailureStrategy stop() {
        return new InitFailureStrategy(true, 0);
    }
}
