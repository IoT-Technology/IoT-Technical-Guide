package iot.technology.gateway.modbus;

import com.serotonin.modbus4j.BatchRead;
import com.serotonin.modbus4j.BatchResults;
import com.serotonin.modbus4j.ModbusMaster;
import com.serotonin.modbus4j.code.DataType;
import com.serotonin.modbus4j.exception.ErrorResponseException;
import com.serotonin.modbus4j.exception.ModbusTransportException;
import com.serotonin.modbus4j.locator.BaseLocator;
import lombok.extern.slf4j.Slf4j;

import java.util.Timer;
import java.util.TimerTask;

/**
 * @author james mu
 * @date 2020/6/20 21:09
 */
@Slf4j
public class ModbusMasterTest {

    private static final Timer get = new Timer("get");

    public static void main(String[] args){
        ModbusMaster modbusMaster = ModbusUtil.createMaster("127.0.0.1", 502);
        BatchRead<String> batch = new BatchRead<>();
        batch.addLocator("1", BaseLocator.holdingRegister(1,0, DataType.TWO_BYTE_INT_SIGNED));
        batch.addLocator("2", BaseLocator.holdingRegister(1, 1, DataType.TWO_BYTE_INT_SIGNED));
        batch.addLocator("3", BaseLocator.holdingRegister(1, 2, DataType.TWO_BYTE_INT_SIGNED));
        batch.addLocator("4", BaseLocator.holdingRegister(1, 3, DataType.TWO_BYTE_INT_SIGNED));

        get.schedule(new TimerTask() {
            @Override
            public void run() {
                try {
                    BatchResults<String> results = ModbusUtil.batchRead(modbusMaster, batch);
                    log.info("slave id 1 读取结果为: " + results);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, 1000, 3000);

    }
}
