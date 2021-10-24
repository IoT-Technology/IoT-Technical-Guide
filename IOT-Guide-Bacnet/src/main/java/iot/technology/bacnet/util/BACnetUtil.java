package iot.technology.bacnet.util;

import com.serotonin.bacnet4j.LocalDevice;
import com.serotonin.bacnet4j.npdu.ip.IpNetwork;
import com.serotonin.bacnet4j.npdu.ip.IpNetworkBuilder;
import com.serotonin.bacnet4j.transport.DefaultTransport;

public class BACnetUtil {

    /**
     * 创建网络对象
     *
     * @param ip 网络地址
     * @param port 端口
     * @return
     */
    public static IpNetwork initIpNetwork(String ip, int port) {
        IpNetwork ipNetwork = new IpNetworkBuilder()
                //本机ip
                .withLocalBindAddress(ip)
                .withSubnet("255.255.255.0", 24)
                .withPort(port)
                .withReuseAddress(true)
                .build();
        return ipNetwork;

    }

    /**
     * 创建虚拟的本地设备， deviceNumber随意
     *
     * @param deviceNumber 设备标识
     * @param ipNetwork 网络对象
     * @return
     */
    public static LocalDevice initLocalDevice(int deviceNumber, IpNetwork ipNetwork) {
        LocalDevice localDevice = new LocalDevice(deviceNumber, new DefaultTransport(ipNetwork));
        try {
            //初始化
            localDevice.initialize();
            localDevice.startRemoteDeviceDiscovery();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return localDevice;
    }
}
