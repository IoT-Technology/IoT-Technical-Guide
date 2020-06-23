package iot.technology.gateway.modbus;

import com.serotonin.modbus4j.BatchRead;
import com.serotonin.modbus4j.BatchResults;
import com.serotonin.modbus4j.ModbusMaster;
import com.serotonin.modbus4j.exception.ErrorResponseException;
import com.serotonin.modbus4j.exception.ModbusInitException;
import com.serotonin.modbus4j.exception.ModbusTransportException;
import com.serotonin.modbus4j.ip.IpParameters;
import lombok.extern.slf4j.Slf4j;


/**
 * @author james mu
 * @date 2020/6/20 20:39
 */
@Slf4j
public class ModbusUtil {

    public static ModbusMaster createMaster(String ip, int port) {
        ModbusMaster master = null;
        try {
            IpParameters ipParam = new IpParameters();
            ipParam.setHost(ip);
            ipParam.setPort(port);
            ipParam.setEncapsulated(false);
            master = ModbusFactoryInstance.getInstance()
                    .createTcpMaster(ipParam, true);
            master.setTimeout(2000);
            master.setRetries(0);
            log.info("Starting Modbus master...");
            master.init();
            log.info("Modbus master started!");
            return master;
        } catch (ModbusInitException e) {
            log.info("Stopping Modbus master...");
            master.destroy();
            log.info("Modbus master stopped!");
        }
        return master;
    }


    public static BatchResults<String> batchRead(ModbusMaster master, BatchRead<String> batchRead)
            throws ErrorResponseException, ModbusTransportException {
       batchRead.setContiguousRequests(false);
       BatchResults<String> result = master.send(batchRead);
       return result;
    }
}
