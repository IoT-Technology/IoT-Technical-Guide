package iot.technology.ratelimiting.controller;

import iot.technology.ratelimiting.vo.AreaVO;
import iot.technology.ratelimiting.vo.RectangleVO;
import iot.technology.ratelimiting.vo.TriangleVO;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author mushuwei
 */
@RestController
@RequestMapping(value = "/api/v1/area", consumes = MediaType.APPLICATION_JSON_VALUE)
public class AreaCalculationController {

    @PostMapping(value = "/rectangle", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AreaVO> rectangle(@RequestBody RectangleVO rectangleVO) {
        return ResponseEntity.ok(new AreaVO("rectangle", rectangleVO.getLength() * rectangleVO.getWidth()));
    }

    @PostMapping(value = "/triangle", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AreaVO> triangle(@RequestBody TriangleVO triangleVO) {
        return ResponseEntity.ok(new AreaVO("triangle", 0.5 * triangleVO.getBottom() * triangleVO.getHeight()));
    }
}
