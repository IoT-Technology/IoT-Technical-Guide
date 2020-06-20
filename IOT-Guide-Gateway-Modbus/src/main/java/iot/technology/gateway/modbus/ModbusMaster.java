package iot.technology.gateway.modbus;

/**
 * @author james mu
 * @date 2020/6/20 21:09
 */
public class ModbusMaster {

    public static void main(String[] args) {
        ModbusUtil.createMaster("localhost", 502);
    }
}
