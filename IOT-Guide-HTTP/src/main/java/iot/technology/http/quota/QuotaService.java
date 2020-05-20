package iot.technology.http.quota;

/**
 * @author james mu
 * @date 2019/8/12 上午9:14
 */
public interface QuotaService {

    boolean isQuotaExceeded(String key);
}
