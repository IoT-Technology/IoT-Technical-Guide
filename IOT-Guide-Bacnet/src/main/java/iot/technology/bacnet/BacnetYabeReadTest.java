package iot.technology.bacnet;

import com.serotonin.bacnet4j.LocalDevice;
import com.serotonin.bacnet4j.RemoteDevice;
import com.serotonin.bacnet4j.exception.BACnetException;
import com.serotonin.bacnet4j.npdu.ip.IpNetwork;
import com.serotonin.bacnet4j.type.primitive.ObjectIdentifier;
import com.serotonin.bacnet4j.util.RequestUtils;
import iot.technology.bacnet.util.BACnetUtil;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
public class BacnetYabeReadTest {

    public static void main(String[] args) throws BACnetException {
        IpNetwork ipNetwork = BACnetUtil.initIpNetwork("172.30.176.1", 47808);
        LocalDevice localDevice = BACnetUtil.initLocalDevice(123, ipNetwork);

        RemoteDevice remoteDevice = localDevice.getRemoteDeviceBlocking(2130082);

        List<ObjectIdentifier> objectList = RequestUtils.getObjectList(localDevice, remoteDevice).getValues();
        objectList.forEach(objectIdentifier -> {
            log.info("objectIdentifier: {}", objectIdentifier);
        });
    }
}
