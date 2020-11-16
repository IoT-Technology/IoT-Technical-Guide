package iot.technology.postgresql.model;


import lombok.Data;

import java.io.Serializable;

/**
 * @author jamesmsw
 * @date 2020/11/15 6:26 下午
 */
@Data
public class Device implements Serializable {

    private static final long serialVersionUID = -6272757841375316024L;

    private Integer id;

    private String name;

    private String additionalInfo;
}
