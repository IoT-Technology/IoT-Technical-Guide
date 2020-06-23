package iot.technology.http.controller;

import com.google.gson.JsonParser;
import iot.technology.http.quota.host.HostRequestsQuotaService;
import iot.technology.tsl.adaptor.JsonConverter;
import iot.technology.tsl.data.kv.AttributeKvEntry;
import iot.technology.tsl.data.kv.KvEntry;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.async.DeferredResult;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.util.*;


/**
 * @Author: 穆书伟
 * @Date: 19-4-2 下午3:46
 * @Version 1.0
 */
@RestController
@RequestMapping("/api/v1")
@Slf4j
public class DeviceApiController {

    @Value("${http.request_timeout}")
    private long defaultTimeout;

    @Autowired(required = false)
    private HostRequestsQuotaService quotaService;

    @RequestMapping(value = "/attributes",method = RequestMethod.POST)
    public DeferredResult<ResponseEntity> postDeviceAttributes(
            @RequestBody String json, HttpServletRequest request) {
        DeferredResult<ResponseEntity> responseWriter = new DeferredResult<ResponseEntity>();
        if (quotaExceeded(request, responseWriter)) {
            return responseWriter;
        }
        responseWriter.setResult(new ResponseEntity<>(HttpStatus.ACCEPTED));
        Set<AttributeKvEntry> attributeKvEntrySet = JsonConverter.convertToAttributes(new JsonParser().parse(json)).getAttributes();
        for (AttributeKvEntry attributeKvEntry : attributeKvEntrySet){
            System.out.println("属性名="+attributeKvEntry.getKey()+" 属性值="+attributeKvEntry.getValueAsString());
        }
        return responseWriter;
    }

    @RequestMapping(value = "/attributes", method = RequestMethod.GET, produces = "application/json")
    public DeferredResult<ResponseEntity> getDeviceAttributes(@RequestParam(value = "clientKeys", required = false, defaultValue = "") String clientKeys,
                                                              @RequestParam(value = "sharedKeys", required = false, defaultValue = "") String sharedKeys,
                                                              HttpServletRequest httpRequest) {
        DeferredResult<ResponseEntity> responseWriter = new DeferredResult<ResponseEntity>();
        if (quotaExceeded(httpRequest, responseWriter)) {
            return responseWriter;
        }
        if (StringUtils.isEmpty(clientKeys) && StringUtils.isEmpty(sharedKeys)) {

        }else {
            Set<String> clientKeySet = !StringUtils.isEmpty(clientKeys) ? new HashSet<>(Arrays.asList(clientKeys.split(","))) : null;
            Set<String> sharedKeySet = !StringUtils.isEmpty(sharedKeys) ? new HashSet<>(Arrays.asList(sharedKeys.split(","))) : null;
        }
        return responseWriter;

    }

    @RequestMapping(value = "/telemetry",method = RequestMethod.POST)
    public DeferredResult<ResponseEntity> postTelemetry(@RequestBody String json, HttpServletRequest request) {
        DeferredResult<ResponseEntity> responseWriter = new DeferredResult<ResponseEntity>();
        responseWriter.setResult(new ResponseEntity("ok", HttpStatus.ACCEPTED));
        if (quotaExceeded(request, responseWriter)) {
            return responseWriter;
        }
        Map<Long, List<KvEntry>> telemetryMaps = JsonConverter.convertToTelemetry(new JsonParser().parse(json)).getData();
            for (Map.Entry<Long,List<KvEntry>> entry : telemetryMaps.entrySet()) {
            System.out.println("key= " + entry.getKey());
            for (KvEntry kvEntry: entry.getValue()) {
                System.out.println("属性名="+kvEntry.getKey()+ " 属性值="+kvEntry.getValueAsString());
            }
        }

        return responseWriter;
    }

    @RequestMapping(value = "/attributes/updates", method = RequestMethod.GET, produces = "application/json")
    public DeferredResult<ResponseEntity> subscribeToAttributes(@RequestParam(value = "timeout", required = false, defaultValue = "0") long timeout,
                                                                HttpServletRequest httpRequest){
        DeferredResult<ResponseEntity> responseWriter = new DeferredResult<ResponseEntity>();
        if (quotaExceeded(httpRequest, responseWriter)) {
            return responseWriter;
        }
        return responseWriter;
    }

    private boolean quotaExceeded(HttpServletRequest request, DeferredResult<ResponseEntity> responseWriter) {

        if (quotaService.isQuotaExceeded(request.getRemoteAddr())) {
            log.warn("REST Quota exceeded for [{}] . Disconnect", request.getRemoteAddr());
            responseWriter.setResult(new ResponseEntity<>(HttpStatus.BANDWIDTH_LIMIT_EXCEEDED));
            return true;
        }
        return false;
    }
}
