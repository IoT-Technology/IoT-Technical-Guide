package iot.technology.custom.protocol;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

/**
 * @author james mu
 * @date 2020/7/27 11:20
 */
@Data
public abstract class Packet {

    @JSONField(serialize = false)
    public abstract byte getCommand();

    @JSONField(deserialize = false, serialize = false)
    private byte swv = 1;

}
