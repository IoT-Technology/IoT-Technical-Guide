package iot.technology.bacnet;

import com.serotonin.bacnet4j.LocalDevice;
import com.serotonin.bacnet4j.RemoteDevice;
import com.serotonin.bacnet4j.exception.BACnetException;
import com.serotonin.bacnet4j.npdu.ip.IpNetwork;
import com.serotonin.bacnet4j.type.enumerated.ObjectType;
import com.serotonin.bacnet4j.type.enumerated.PropertyIdentifier;
import com.serotonin.bacnet4j.type.primitive.ObjectIdentifier;
import com.serotonin.bacnet4j.type.primitive.Real;
import com.serotonin.bacnet4j.util.RequestUtils;
import com.serotonin.bacnet4j.type.primitive.Boolean;
import iot.technology.bacnet.util.BACnetUtil;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CancellationException;

@Slf4j
public class BacnetYabeWriteTest {

    public static void main(String[] args) throws Exception {
        IpNetwork ipNetwork = BACnetUtil.initIpNetwork("172.20.16.1", 47808);
        LocalDevice localDevice = BACnetUtil.initLocalDevice(123, ipNetwork);

        try {
            RemoteDevice remoteDevice = localDevice.getRemoteDevice(3309642).get();
            //必须先修改out of service为true
            RequestUtils.writeProperty(localDevice, remoteDevice, new ObjectIdentifier(ObjectType.analogValue, 0), PropertyIdentifier.outOfService, Boolean.TRUE);
            Thread.sleep(1000);
            //修改属性值
            RequestUtils.writePresentValue(localDevice, remoteDevice, new ObjectIdentifier(ObjectType.analogValue, 0), new Real(76));
            Thread.sleep(2000);
            log.info("analogValue0: {}", RequestUtils.readProperty(localDevice, remoteDevice, new ObjectIdentifier(ObjectType.analogValue, 0),
                    PropertyIdentifier.presentValue, null));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            localDevice.terminate();
        }

    }
}
