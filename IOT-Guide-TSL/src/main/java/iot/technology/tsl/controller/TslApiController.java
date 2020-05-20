package iot.technology.tsl.controller;

import com.google.gson.JsonParser;
import iot.technology.tsl.adaptor.JsonConverter;
import iot.technology.tsl.data.kv.KvEntry;
import iot.technology.tsl.data.model.ResponseModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.async.DeferredResult;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @Author: 穆书伟
 * @Date: 19-4-16
 * @Version 1.0
 */
@RestController
@RequestMapping("/api/v1")
public class TslApiController {
    @RequestMapping(value = "/tsl",method = RequestMethod.POST)
    public DeferredResult<ResponseEntity> postJson(@RequestBody String json){
        DeferredResult<ResponseEntity> responseWriter = new DeferredResult<ResponseEntity>();
        Map<Long, List<KvEntry>> telemetryMaps = JsonConverter.convertToTelemetry(new JsonParser().parse(json)).getData();
        List<ResponseModel> responseModelList = new ArrayList<>();

        for (Map.Entry<Long,List<KvEntry>> entry : telemetryMaps.entrySet()) {
            for (KvEntry kvEntry: entry.getValue()) {
                ResponseModel responseModel = new ResponseModel();
                responseModel.setKey(kvEntry.getKey());
                switch (kvEntry.getDataType()) {
                    case STRING:
                        responseModel.setValue("String value="+kvEntry.getValueAsString());
                        responseModelList.add(responseModel);
                        break;
                    case DOUBLE:
                        responseModel.setValue("Double value="+kvEntry.getValueAsString());
                        responseModelList.add(responseModel);
                        break;
                    case LONG:
                        responseModel.setValue("Long value="+kvEntry.getValueAsString());
                        responseModelList.add(responseModel);
                        break;
                    case BOOLEAN:
                        responseModel.setValue("Boolean value="+kvEntry.getValueAsString());
                        responseModelList.add(responseModel);
                        break;
                }

            }
        }
        responseWriter.setResult(new ResponseEntity(responseModelList,HttpStatus.ACCEPTED));
        return responseWriter;
    }
}
