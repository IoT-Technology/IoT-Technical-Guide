package iot.technology.bacnet;

import com.serotonin.bacnet4j.LocalDevice;
import com.serotonin.bacnet4j.RemoteDevice;
import com.serotonin.bacnet4j.exception.BACnetException;
import com.serotonin.bacnet4j.npdu.ip.IpNetwork;
import com.serotonin.bacnet4j.type.Encodable;
import com.serotonin.bacnet4j.type.enumerated.ObjectType;
import com.serotonin.bacnet4j.type.enumerated.PropertyIdentifier;
import com.serotonin.bacnet4j.type.primitive.ObjectIdentifier;
import com.serotonin.bacnet4j.type.primitive.UnsignedInteger;
import com.serotonin.bacnet4j.util.PropertyValues;
import com.serotonin.bacnet4j.util.ReadListener;
import com.serotonin.bacnet4j.util.RequestUtils;
import iot.technology.bacnet.util.BACnetUtil;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.List;

@Slf4j
public class BacnetYabeReadTest {

    public static void main(String[] args) throws BACnetException {
        IpNetwork ipNetwork = BACnetUtil.initIpNetwork("172.20.16.1", 47808);
        LocalDevice localDevice = BACnetUtil.initLocalDevice(123, ipNetwork);

        try {
            RemoteDevice remoteDevice = localDevice.getRemoteDeviceBlocking(3309642);

            log.info("modelName: {}", remoteDevice.getDeviceProperty(PropertyIdentifier.modelName));
            log.info("analogInput2: {}", RequestUtils.readProperty(localDevice, remoteDevice,
                            new ObjectIdentifier(ObjectType.analogInput, 2),
                            PropertyIdentifier.presentValue, null));

            List<ObjectIdentifier> objectList = RequestUtils.getObjectList(localDevice, remoteDevice).getValues();
            objectList.forEach(objectIdentifier -> {
                log.info("objectIdentifier: {}", objectIdentifier);
            });
            ObjectIdentifier oid = new ObjectIdentifier(ObjectType.analogInput, 0);
            ObjectIdentifier oid1 = new ObjectIdentifier(ObjectType.analogInput, 1);
            ObjectIdentifier oid2 = new ObjectIdentifier(ObjectType.analogInput, 2);

            //获取指定的presentValue
            PropertyValues pvs = RequestUtils.readOidPresentValues(localDevice, remoteDevice, Arrays.asList(oid,oid1,oid2), new ReadListener(){
                @Override
                public boolean progress(double progress, int deviceId,
                                        ObjectIdentifier oid, PropertyIdentifier pid,
                                        UnsignedInteger pin, Encodable value) {
                    log.info("========");
                    log.info("progress=" + progress);
                    log.info("deviceId=" + deviceId);
                    log.info("oid="+oid.toString());
                    log.info("pid="+pid.toString());
                    log.info("UnsignedInteger="+pin);
                    log.info("value="+value.toString() + "  getClass =" +value.getClass());
                    return false;
                }

            });
            Thread.sleep(3000);
            log.info("analogInput:0 == " + pvs.get(oid, PropertyIdentifier.presentValue));
            //获取指定的presentValue
            PropertyValues pvs2 = RequestUtils.readOidPresentValues(localDevice, remoteDevice, Arrays.asList(oid,oid1,oid2),null);
            log.info("analogInput:1 == " + pvs2.get(oid1, PropertyIdentifier.presentValue));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            localDevice.terminate();
        }
    }
}
