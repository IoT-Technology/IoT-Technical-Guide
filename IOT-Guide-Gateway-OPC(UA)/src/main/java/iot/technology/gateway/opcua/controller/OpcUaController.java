package iot.technology.gateway.opcua.controller;

import iot.technology.gateway.opcua.client.ClientHandler;
import iot.technology.gateway.opcua.entity.NodeEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author james mu
 * @date 2020/7/11 14:11
 */
@Controller
public class OpcUaController {

    @Autowired
    private ClientHandler clientHandler;

    @RequestMapping("connect")
    @ResponseBody
    public String connect() {
        try {
            return clientHandler.connect();
        } catch (Exception e) {
            e.printStackTrace();
            return "fail";
        }
    }

    @RequestMapping("/disconnect")
    @ResponseBody
    public String disconnect() {
        try {
            return clientHandler.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
            return "fail";
        }
    }

    /**
     * @MethodName: subscribe
     * @Description: subscribe
     * @return
     */
    @RequestMapping("/subscribe")
    @ResponseBody
    public String subscribe(HttpServletRequest request) {

        try {
            List<NodeEntity> nodes = Stream.of(request.getParameter("id").split(","))
                    .map(id -> NodeEntity.builder()
                            .index(Integer.valueOf(request.getParameter("index")))
                            .identifier(id).build())
                    .collect(Collectors.toList());

            return clientHandler.subscribe(nodes);
        } catch (Exception e) {
            e.printStackTrace();
            return "fail";
        }
    }

    @RequestMapping("/write")
    @ResponseBody
    public String write(HttpServletRequest request) {
        NodeEntity node = NodeEntity.builder()
                .index(Integer.valueOf(request.getParameter("index")))
                .identifier(request.getParameter("id"))
                .value(request.getParameter("value"))
                .type(request.getParameter("type"))
                .build();
        try {
            return clientHandler.write(node);
        } catch (Exception e) {
            e.printStackTrace();
            return "fail";
        }
    }

    @RequestMapping("/read")
    @ResponseBody
    public String read(HttpServletRequest request)  {
        NodeEntity node = NodeEntity.builder()
                .index(Integer.valueOf(request.getParameter("index")))
                .identifier(request.getParameter("id"))
                .build();
        try {
            return clientHandler.read(node);
        } catch (Exception e) {
            e.printStackTrace();
            return "fail";
        }
    }
}
