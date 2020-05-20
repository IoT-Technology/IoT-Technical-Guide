package iot.technology.token.jwt.extractor;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author: 穆书伟
 * @Date: 19-4-10 上午9:34
 * @Version 1.0
 */
public interface TokenExtractor {

    String extract(HttpServletRequest request);
}
