package iot.technology.postgresql.model;

import lombok.Data;

import java.io.Serializable;

/**
 * @author jamesmsw
 * @date 2020/11/15 7:38 下午
 */
@Data
public class TsKv implements Serializable {
    private static final long serialVersionUID = -6031745104111504815L;

    private Integer id;

    private Integer deviceId;

    private String key;

    private Long ts;

    private Boolean booleanValue;

    private String strValue;

    private Long longValue;

    private Double doubleValue;

    private String jsonValue;
}
