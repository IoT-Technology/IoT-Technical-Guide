package iot.technology.postgresql.model;

import lombok.Data;

import java.io.Serializable;

/**
 * @author jamesmsw
 * @date 2020/11/15 7:39 下午
 */
@Data
public class TsKvLatest implements Serializable {
    private static final long serialVersionUID = 7947621980950226416L;

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
