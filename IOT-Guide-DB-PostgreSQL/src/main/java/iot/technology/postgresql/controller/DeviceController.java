package iot.technology.postgresql.controller;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;
import iot.technology.postgresql.model.Device;
import iot.technology.postgresql.model.TsKvLatest;
import iot.technology.postgresql.service.DeviceService;
import iot.technology.postgresql.service.TimeseriesService;
import iot.technology.tsl.adaptor.JsonConverter;
import iot.technology.tsl.data.kv.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.async.DeferredResult;

import javax.annotation.Resource;
import java.util.*;

/**
 * @author jamesmsw
 * @date 2020/11/15 8:37 下午
 */
@RestController
@RequestMapping("/api")
@Slf4j
public class DeviceController {

    @Resource(name = "deviceService")
    private DeviceService deviceService;
    @Resource(name = "timeseriesService")
    private TimeseriesService timeseriesService;

    @RequestMapping(value = "/device/save", method = RequestMethod.POST)
    @ResponseBody
    public Device saveDevice(@RequestBody Device device) {
        return deviceService.saveDevice(device);
    }

    @RequestMapping(value = "/{deviceId}/timeseries", method = RequestMethod.POST)
    @ResponseBody
    public DeferredResult<ResponseEntity> saveDeviceTelemetry(@PathVariable("deviceId") Integer deviceId,
                                                              @RequestBody String requestBody) {
        return saveTelemetry(deviceId, requestBody, 0L);
    }

    @RequestMapping(value = "/{deviceId}/timeseries/{ttl}", method = RequestMethod.POST)
    @ResponseBody
    public DeferredResult<ResponseEntity> saveDeviceTelemetryWithTTL(@PathVariable("deviceId") Integer deviceId,
                                                                     @PathVariable("ttl") Long ttl,
                                                                     @RequestBody String requestBody) {
        return saveTelemetry(deviceId, requestBody, ttl);
    }

    @RequestMapping(value = "/{deviceId}/values/timeseries", method = RequestMethod.GET)
    @ResponseBody
    public DeferredResult<ResponseEntity> getLatestTimeseries(@PathVariable("deviceId") Integer deviceId,
                                                              @RequestParam(name = "keys", required = false) String keysStr) {
        List<TsKvLatest> tsKvLatests;
        if (StringUtils.isEmpty(keysStr)) {
            tsKvLatests = timeseriesService.findAllLatest(deviceId);
        } else {
            tsKvLatests = timeseriesService.findLatest(deviceId, toKeysList(keysStr));
        }
        List<TsKvEntry> tsKvEntries = coventTsKvEntry(tsKvLatests);
        final DeferredResult<ResponseEntity> result = new DeferredResult<>();
        result.setResult(new ResponseEntity<>(tsKvEntries, HttpStatus.OK));
        return result;
    }

    private DeferredResult<ResponseEntity> getImmediateDeferredResult(String message, HttpStatus status) {
        DeferredResult<ResponseEntity> result = new DeferredResult<>();
        result.setResult(new ResponseEntity<>(message, status));
        return result;
    }

    private DeferredResult<ResponseEntity> saveTelemetry(Integer deviceId, String requestBody, long ttl) {
        Map<Long, List<KvEntry>> telemetryRequest;
        JsonElement telemetryJson;
        try {
            telemetryJson = new JsonParser().parse(requestBody);
        } catch (JsonSyntaxException e) {
            return getImmediateDeferredResult("Unable to parse timeseries payload: Invalid JSON body!", HttpStatus.BAD_REQUEST);
        }
        try {
            telemetryRequest = JsonConverter.convertToTelemetry(telemetryJson).getData();
        } catch (JsonSyntaxException e) {
            return getImmediateDeferredResult("Unable to parse timeseries payload. Invalid JSON body: " + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        List<TsKvEntry> entries = new ArrayList<>();
        for (Map.Entry<Long, List<KvEntry>> entry : telemetryRequest.entrySet()) {
            for (KvEntry kv : entry.getValue()) {
                entries.add(new BasicTsKvEntry(entry.getKey(), kv));
            }
        }
        if (entries.isEmpty()) {
            return getImmediateDeferredResult("No timeseries data found in request body!", HttpStatus.BAD_REQUEST);
        }
        timeseriesService.save(deviceId, entries, ttl);
        return getImmediateDeferredResult("success", HttpStatus.OK);
    }

    private List<String> toKeysList(String keys) {
        List<String> keyList = null;
        if (!StringUtils.isEmpty(keys)) {
            keyList = Arrays.asList(keys.split(","));
        }
        return keyList;
    }

    private List<TsKvEntry> coventTsKvEntry(List<TsKvLatest> tsKvLatests) {
        List<TsKvEntry> tsKvEntries = new ArrayList<>();
        tsKvLatests.forEach(tsKvLatest -> {
            BasicKvEntry basicKvEntry = null;
            if (tsKvLatest.getStrValue() != null) {
                basicKvEntry = new StringDataEntry(tsKvLatest.getKey(), tsKvLatest.getStrValue());
            }
            if (tsKvLatest.getLongValue() != null) {
                basicKvEntry = new BooleanDataEntry(tsKvLatest.getKey(), tsKvLatest.getBooleanValue());
            }
            if (tsKvLatest.getDoubleValue() != null) {
                basicKvEntry = new DoubleDataEntry(tsKvLatest.getKey(), tsKvLatest.getDoubleValue());
            }
            if (tsKvLatest.getBooleanValue() != null) {
                basicKvEntry = new BooleanDataEntry(tsKvLatest.getKey(), tsKvLatest.getBooleanValue());
            }
            if (tsKvLatest.getJsonValue() != null) {
                basicKvEntry = new JsonDataEntry(tsKvLatest.getKey(), tsKvLatest.getJsonValue());
            }
            BasicTsKvEntry tsKvEntry = new BasicTsKvEntry(tsKvLatest.getTs(), basicKvEntry);
            tsKvEntries.add(tsKvEntry);
        });
        return tsKvEntries;
    }


}
