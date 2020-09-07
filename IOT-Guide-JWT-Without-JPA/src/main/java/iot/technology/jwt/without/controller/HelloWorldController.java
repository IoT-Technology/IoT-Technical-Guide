package iot.technology.jwt.without.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author james mu
 * @date 2020/9/7 19:18
 */
@RestController
@CrossOrigin
public class HelloWorldController {

    @RequestMapping({ "/hello" })
    public String hello() {
        return "Hello World";
    }

}
