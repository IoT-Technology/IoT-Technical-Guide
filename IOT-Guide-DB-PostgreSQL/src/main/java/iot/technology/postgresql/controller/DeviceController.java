package iot.technology.postgresql.controller;

import iot.technology.postgresql.model.Device;
import iot.technology.postgresql.service.DeviceService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author jamesmsw
 * @date 2020/11/15 8:37 下午
 */
@RestController
@RequestMapping("/api")
public class DeviceController {

    @Resource(name = "deviceService")
    private DeviceService deviceService;

    @RequestMapping(value = "/device/save", method = RequestMethod.POST)
    @ResponseBody
    public Device saveDevice(@RequestParam(name = "name") String name,
                             @RequestParam(name = "additionalInfo") String additionalInfo) {
        Device device = new Device();
        device.setName(name);
        device.setAdditionalInfo(additionalInfo);
        return deviceService.saveDevice(device);
    }

    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String test() {
        return "Hello World!";
    }
}
