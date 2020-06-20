package iot.technology.gateway.modbus;

import com.serotonin.modbus4j.ModbusFactory;

import java.util.Objects;

/**
 * @author james mu
 * @date 2020/6/20 20:10
 */
public final class ModbusFactoryInstance {

    private volatile static ModbusFactory modbusFactory;

    private ModbusFactoryInstance(){

    }

    public static ModbusFactory getInstance() {
        if (Objects.isNull(modbusFactory)){
            synchronized (ModbusFactoryInstance.class) {
                if (Objects.isNull(modbusFactory)){
                    modbusFactory = new ModbusFactory();
                }
            }
        }
        return modbusFactory;
    }
}
