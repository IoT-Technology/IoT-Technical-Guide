package iot.technology.gateway.opcua.client;

import com.google.common.collect.ImmutableList;
import iot.technology.gateway.opcua.entity.NodeEntity;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.milo.opcua.sdk.client.OpcUaClient;
import org.eclipse.milo.opcua.sdk.client.api.nodes.VariableNode;
import org.eclipse.milo.opcua.sdk.client.api.subscriptions.UaSubscription;
import org.eclipse.milo.opcua.stack.core.AttributeId;
import org.eclipse.milo.opcua.stack.core.BuiltinDataType;
import org.eclipse.milo.opcua.stack.core.types.builtin.*;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.Unsigned;
import org.eclipse.milo.opcua.stack.core.types.enumerated.MonitoringMode;
import org.eclipse.milo.opcua.stack.core.types.enumerated.TimestampsToReturn;
import org.eclipse.milo.opcua.stack.core.types.structured.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author james mu
 * @date 2020/7/10 09:32
 */
@Slf4j
@Service
public class ClientHandler {

    private final ClientRunner clientRunner;

    @Autowired
    public ClientHandler(ClientRunner clientRunner) {
        this.clientRunner = clientRunner;
    }

    /**
     * 客户端实例
     */
    private OpcUaClient client = null;

    /**
     * 客户端初始化
     *
     * @return
     */
    public String connect() throws Exception {
        if (Objects.nonNull(client)) {
            return "客户端已创建";
        }
        client = clientRunner.run();
        if (Objects.isNull(client)) {
            return "客户端配置实例化失败";
        }
        //创建连接
        client.connect().get();
        return "创建连接成功";
    }

    /**
     * @MethodName: subscribe
     * @Description: 订阅节点变量
     * @throws Exception
     */
    public String subscribe(List<NodeEntity> nodes) throws Exception {

        if (client == null) {
            return "找不到客户端，操作失败";
        }

        // 查询订阅对象，没有则创建
        UaSubscription subscription = null;
        ImmutableList<UaSubscription> subscriptionList = client.getSubscriptionManager().getSubscriptions();
        if (CollectionUtils.isEmpty(subscriptionList)) {
            subscription = client.getSubscriptionManager().createSubscription(1000.0).get();
        } else {
            subscription = subscriptionList.get(0);
        }

        // 监控项请求列表
        List<MonitoredItemCreateRequest> requests = new ArrayList<>();

        if (!CollectionUtils.isEmpty(nodes)) {
            for (NodeEntity node : nodes) {
                // 创建监控的参数
                MonitoringParameters parameters = new MonitoringParameters(subscription.nextClientHandle(), 1000.0, // sampling
                        // interval
                        null, // filter, null means use default
                        Unsigned.uint(10), // queue size
                        true // discard oldest
                );
                // 创建订阅的变量， 创建监控项请 求
                MonitoredItemCreateRequest request = new MonitoredItemCreateRequest(
                        new ReadValueId(new NodeId(node.getIndex(), node.getIdentifier()), AttributeId.Value.uid(),
                                null, null),
                        MonitoringMode.Reporting, parameters);
                requests.add(request);
            }
        }

        // 创建监控项，并且注册变量值改变时候的回调函数
        subscription.createMonitoredItems(TimestampsToReturn.Both, requests, (item, id) -> {
            item.setValueConsumer((i, v) -> {
                log.info("item={}, value={}", i.getReadValueId().getNodeId(), v.getValue());
            });
        }).get();

        return "订阅成功";
    }

    /**
     * 断开连接
     * @return
     */
    public String disconnect() {
        if (Objects.isNull(client)) {
            return "连接已断开";
        }
        clientRunner.getFuture().complete(client);
        client = null;
        return "断开连接成功";
    }


    /**
     * @MethodName: write
     * @Description: 变节点量写入
     * @param node
     * @throws Exception
     */
    public String write(NodeEntity node) throws Exception {

        if (client == null) {
            return "找不到客户端，操作失败";
        }

        NodeId nodeId = new NodeId(node.getIndex(), node.getIdentifier());
        Variant value = null;
        switch (node.getType()) {
            case "int":
                value = new Variant(Integer.parseInt(node.getValue()));
                break;
            case "boolean":
                value = new Variant(Boolean.parseBoolean(node.getValue()));
                break;
            case "double":
                value = new Variant(Double.parseDouble(node.getValue()));
                break;
            case "string":
                value = new Variant(node.getValue());
                break;
        }
        DataValue dataValue = new DataValue(value, null, null);

        StatusCode statusCode = client.writeValue(nodeId, dataValue).get();

        return "节点【" + node.getIdentifier() + "】写入状态：" + statusCode.isGood();
    }

    /**
     * 读取节点数据
     *
     * @return
     * @throws Exception
     */
    public String read(NodeEntity node) throws Exception {
        if (client == null) {
            return "找不到客户端，操作失败";
        }

        NodeId nodeId = new NodeId(node.getIndex(), node.getIdentifier());
        VariableNode vnode = client.getAddressSpace().createVariableNode(nodeId);
        DataValue value = vnode.readValue().get();
        log.info("Value={}", value);

        Variant variant = value.getValue();
        log.info("Variant={}", variant.getValue());

        log.info("BackingClass={}", BuiltinDataType.getBackingClass(variant.getDataType().get()));

        return "节点【" + node.getIdentifier() + "】：" + variant.getValue();
    }
}
