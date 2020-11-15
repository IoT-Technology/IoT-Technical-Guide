package iot.technology.postgresql.model;


import java.io.Serializable;

/**
 * @author jamesmsw
 * @date 2020/11/15 6:26 下午
 */
public class Device implements Serializable {

    private static final long serialVersionUID = -6272757841375316024L;

    private Integer id;

    private String name;

    private String additionalInfo;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAdditionalInfo() {
        return additionalInfo;
    }

    public void setAdditionalInfo(String additionalInfo) {
        this.additionalInfo = additionalInfo;
    }
}
