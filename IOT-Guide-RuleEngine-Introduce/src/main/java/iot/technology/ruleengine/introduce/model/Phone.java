package iot.technology.ruleengine.introduce.model;


import lombok.Data;

/**
 * @author jamesmsw
 * @date 2021/1/26 9:54 上午
 */
@Data
public class Phone {

    private String model;
    private OSType osType;

    public Phone(OSType osType) {
        this.osType = osType;
    }

}
