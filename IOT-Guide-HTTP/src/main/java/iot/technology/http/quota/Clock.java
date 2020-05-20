package iot.technology.http.quota;

/**
 * @author james mu
 * @date 2019/8/9 下午4:59
 */
public final class Clock {
    private static long time = 0L;

    private Clock() {

    }

    public static long millis() {
        return time == 0 ? System.currentTimeMillis() : time;
    }

    public static void setMills(long mills) {
        time = mills;
    }

    public static void shift(long delta) {
        time += delta;
    }

    public static void reset() {
        time = 0;
    }

}
