<p align="center">
    <img src="https://james-1258744956.cos.ap-shanghai.myqcloud.com/IOT%20Technical%20Guide/logo.png" alt="IOT Technical Guide">
</p>
<p align="center">
    <a href="https://github.com/IoT-Technology/IOT-Technical-Guide/commits/" title="Last Commit"><img src="https://img.shields.io/github/last-commit/IoT-Technology/IOT-Technical-Guide?style=flat"></a>
    <a href="https://travis-ci.org/IoT-Technology/IOT-Technical-Guide" title="Travis CI"><img src="https://travis-ci.org/IoT-Technology/IOT-Technical-Guide.svg?branch=master"/></a>
    <a href="https://github.com/IoT-Technology/IOT-Technical-Guide/blob/master/LICENSE" title="License"><img src="https://img.shields.io/badge/License-Apache%202.0-green.svg?style=flat"></a>
    <a href="https://github.com/IoT-Technology/IOT-Technical-Guide/issues" title="Open Issues"><img src="https://img.shields.io/github/issues/IoT-Technology/IOT-Technical-Guide?style=flat"></a>
</p>
<p align="center">
    <a href="https://visitor-badge.glitch.me/badge?page_id=sanshengshui.iot_technology_guide"><img src="https://visitor-badge.glitch.me/badge?page_id=sanshengshui.iot_technology_guide" alt="访客"></a>
    <a href="https://blog.mushuwei.cn/categories/IOT-Technical-Guide/"><img src="https://img.shields.io/badge/阅读-read-brightgreen.svg" alt="阅读"></a>
    <a href="#投稿"><img src="https://img.shields.io/badge/support-投稿-critical.svg" alt="投稿"></a>
    <a href="https://gitter.im/sanshengshui/IoT-Technology-Guide?utm_source=badge&utm_medium=badge&utm_campaign=pr-badge"><img src="https://badges.gitter.im/sanshengshui/IoT-Technology-Guide.svg"></a>
    <a href="https://github.com/IoT-Technology/IOT-Technical-Guide/stargazers" title="Stars"><img src="https://img.shields.io/github/stars/IoT-Technology/IOT-Technical-Guide.svg?style=social&label=Star"></a>
    <a href="https://github.com/IoT-Technology/IOT-Technical-Guide/network/members" title="Fork Members"><img src="https://img.shields.io/github/forks/IoT-Technology/IOT-Technical-Guide.svg?style=social&label=Fork"></a>
    <a href="https://github.com/IoT-Technology/IOT-Technical-Guide/watchers" title="Watchers"><img src="https://img.shields.io/github/watchers/IoT-Technology/IOT-Technical-Guide.svg?style=social&label=Watch"></a>
</p>


 **:maple_leaf:高质量的 IOT 技术教程，代码主要源于国外开源物联网平台[ThingsBoard](https://thingsboard.io/)和对阿里云物联网平台的感悟**




<p align="center">
    <img src="https://james-1258744956.cos.ap-shanghai.myqcloud.com/thingsboard-mqtt-part2/halving_line.jpg" alt="分割线">
</p>
<p align="center">

------

[📖 Thingsboard源码分析](README_Thingsboard.md) | 📖 从0搭建高性能IoT平台和实践

------




### a.『 预习篇 』

-  [《新基建和5G风口下的物联网平台》](https://iot.mushuwei.cn/#/iot-platform)<br>

> 物联网正处于元年，赶紧学习起来吧！
>
> 物联网平台是物联网架构中的核心部分，它连接现实世界和虚拟世界，以实现对象之间的通信，管理数据流，支持应用程序开发并为连接的物联网设备提供基本分析

．·°∴☆．．·°．·°∴☆．．·°．·°∴☆




-  [《理解SaaS多租户应用的架构和设计》](https://iot.mushuwei.cn/#/saas)<br>

> SaaS和PaaS行业的需求是日益增长的！
>
> 聊到PaaS、SaaS，就不得不谈到多租户。

．·°∴☆．．·°．·°∴☆．．·°．·°∴☆



### b.『 设备接入篇 』

-  ![](https://james-1258744956.cos.ap-shanghai.myqcloud.com/IOT%20Technical%20Guide/MQTT.png)[《白话MQTT基础知识和入门》](https://iot.mushuwei.cn/#/mqtt-basic)<br>
-  [《实现百万并发MQTT服务端》](https://iot.mushuwei.cn/#/high-performance-mqtt-server) 对于工程 [IOT-Guide-MQTT](https://github.com/IoT-Technology/IOT-Technical-Guide/tree/master/IOT-Guide-MQTT)<br>

> MQTT(消息队列遥测传输) 是ISO标准下基于发布(Publish)/订阅(Subscribe)范式的消息协议，
>
> 同样也是物联网场景下使用最多的协议，是事实上的物联网“标准”协议。
>
> 大家普遍对如何实现或者正在寻找一款适合自己业务和技术的MQTT 服务端。
>
> 那么通过学习本文，你将能实现百万并发MQTT服务端。

．·°∴☆．．·°．·°∴☆．．·°．·°∴☆



-  ![](https://james-1258744956.cos.ap-shanghai.myqcloud.com/IOT%20Technical%20Guide/coap.png)[《初识CoAP并抓住它的"心"》](https://iot.mushuwei.cn/#/coap-basic)<br>
-  [《100行代码快速搭建功能完备的CoAP服务》](https://iot.mushuwei.cn/#/100-lines-of-code-realize-coap-service) 对应工程 [IOT-Guide-Coap](https://github.com/IoT-Technology/IOT-Technical-Guide/tree/master/IOT-Guide-Coap)<br>

> 节点通常具有带少量ROM和RAM的8位微控制器，而诸如低功耗无线个人局域网 （6LoWPAN）上的IPv6之类的受限网络通常具有较高的分组错误率，典型吞吐量为10 kbps 。该协议旨在用于机器对机器（M2M）应用，例如智能能源和楼宇自动化。
>
> 接下来，我将通过100行代码为大家搭建一个功能完备的CoAP服务器。

．·°∴☆．．·°．·°∴☆．．·°．·°∴☆



-  ![](https://james-1258744956.cos.ap-shanghai.myqcloud.com/IOT%20Technical%20Guide/HTTP.png)[《重新认识HTTP协议并管理设备》](https://iot.mushuwei.cn/#/rethinking-http) 对应工程[IOT-Guide-HTTP](https://github.com/IoT-Technology/IOT-Technical-Guide/tree/master/IOT-Guide-HTTP)<br>

> HTTP是可用于IoT应用程序的通用网络协议。您可以在此处找到有关HTTP的更多信息。

．·°∴☆．．·°．·°∴☆．．·°．·°∴☆



-  [《实践案例: 车载终端设备的数据解析》](https://iot.mushuwei.cn/#/decoder-encoder-equipment-protocol) 对应工程[IOT-Guide-Custom-Protocol](https://github.com/IoT-Technology/IOT-Technical-Guide/tree/master/IOT-Guide-Custom-Protocol)<br>

> 在非标准设备中，自定义协议的数据解析成为必不可少的技能。
>
> 通过本文和工程的学习后，你将轻松应付一切！

．·°∴☆．．·°．·°∴☆．．·°．·°∴☆



### c.『 边缘计算篇 』

- ![](https://james-1258744956.cos.ap-shanghai.myqcloud.com/gateway/modbus.jpg?imageMogr2/thumbnail/!50p)[《工业领域下的Modbus网关设备》](https://iot.mushuwei.cn/#/gateway-modbus) 对应工程 [IOT-Guide-Gateway-Modbus](https://github.com/IoT-Technology/IOT-Technical-Guide/tree/master/IOT-Guide-Gateway-Modbus)<br>

> 本文通过实战用python对工业Modbus网关设备进行数据读取和写入，
>
> 并讲解pymodbus和Modbus协议的Master/Slave架构特点。




- ![](https://james-1258744956.cos.ap-shanghai.myqcloud.com/gateway/opcUA-logo.jpg?imageMogr2/thumbnail/!60p)[《工业领域下的OPC-UA网关设备》](https://iot.mushuwei.cn/#/gateway-opcua) 对应工程 [IOT-Guide-Gateway-OPC(UA)](https://github.com/IoT-Technology/IOT-Technical-Guide/tree/master/IOT-Guide-Gateway-OPC(UA))

>本文通过实战用python在工业OPC-UA网关设备高效的性能和占用内存小的特点，
>
>并讲解OPC-UA在工业领域的野心。



### d.『 设备、接口认证和安全篇 』

- ![](https://james-1258744956.cos.ap-shanghai.myqcloud.com/IOT%20Technical%20Guide/security.png)**Spring Security能量**

> 本文是通过Spring Security在接口认证运用的案例，讲解接口安全的重要性。
>
> 并简述Spring Security的知识点。

- [x] [JWT(JSON Web Token)是什么?](https://iot.mushuwei.cn/#/what-jwt)
- [x] [Spring Boot Security + JWT示例](https://iot.mushuwei.cn/#/spring-security-without-jpa) 对应工程 [IOT-Guide-JWT-Without-JPA](https://github.com/IoT-Technology/IOT-Technical-Guide/tree/master/IOT-Guide-JWT-Without-JPA)
- [x] [Spring Boot Security + JWT + MySQL示例](https://iot.mushuwei.cn/#/spring-security-jpa) 对应工程 [IOT-Guide-JWT-JPA](https://github.com/IoT-Technology/IOT-Technical-Guide/tree/master/IOT-Guide-JWT-JPA)

- ![](https://james-1258744956.cos.ap-shanghai.myqcloud.com/IOT%20Technical%20Guide/oauth-2-sm.png?imageMogr2/thumbnail/!50p)[《下一代安全实践 OAuth2》](https://iot.mushuwei.cn/#/oauth2)

> 本文是通过OAuth2在设备认证的案例来解决设备的一个机器一个密码。
>
> 运用它你无需通过繁多的设置来配置设备客户端了。

- [x] [OAuth2.0 最简单的指南](https://iot.mushuwei.cn/#/oauth2-guide)



### e.『 实时显示篇 』

- ![](https://james-1258744956.cos.ap-shanghai.myqcloud.com/IOT%20Technical%20Guide/Websocket.png)[《WebSocket技术魔法》](https://iot.mushuwei.cn/#/websocket)

> 数据在页面上的实时显示是当前大多数人的需求，WebSocket可以让梦想成真，
>
> 而IoT对这种需求是必须的，所以学习WebSocket就变成了必须的技能。

### f.『 数据交换和序列化篇 』

- ![](https://james-1258744956.cos.ap-shanghai.myqcloud.com/IOT%20Technical%20Guide/language.png)[《设备的语言-物模型》](https://iot.mushuwei.cn/#/tsl)　

> 物模型是一个Json格式的文件。它是物理空间中的实体，如传感器、车载装置、楼宇、工厂等在云端的数字化表示，你对实现这样的功能感到好奇吗？相信我，在这里，你将得到它。



- ![](https://james-1258744956.cos.ap-shanghai.myqcloud.com/IOT%20Technical%20Guide/buffer.png)[《平台无关并具有扩展性的ProtoBuf》](https://iot.mushuwei.cn/#/protobuf)

> 本文是通过ProtoBuf在IoT设备协议和数据的序列化上，去讲解Protobuf高效，不依赖语言的特点。
>
> 即定义一个文件，到处都可用的优点。

### g.『 存储和查询篇 』

- ![](https://james-1258744956.cos.ap-shanghai.myqcloud.com/IOT%20Technical%20Guide/MongoDB-Logo.svg.png?imageMogr2/thumbnail/!40p)[《PostgreSQL碰撞IoT》](https://iot.mushuwei.cn/#/postgresql-iot)

- ![](https://james-1258744956.cos.ap-shanghai.myqcloud.com/IOT%20Technical%20Guide/elephant.png?imageMogr2/thumbnail/!10p)[《MongoDB在IoT中的应用》](https://iot.mushuwei.cn/#/mongodb-iot)

### h.『 流处理和消息队列篇 』

- [《教你如何简单实现一个消息队列》](https://iot.mushuwei.cn/#/how-to-build-queque)


- ![](https://james-1258744956.cos.ap-shanghai.myqcloud.com/IOT%20Technical%20Guide/kafka.png)[《广受好评的Kafka》](https://iot.mushuwei.cn/#/kafka)

> 本文是通过Kafka在流处理和应用解耦的特点，更好、更高效的处理数据分析。



- ![](https://james-1258744956.cos.ap-shanghai.myqcloud.com/IOT%20Technical%20Guide/RabbitMQ-logo.svg)[《从未缺席的RabbitMQ》](https://iot.mushuwei.cn/#/rabbitmq)

> 本文是通过另一个消息中间件RabbitMQ在IoT设备数据的应用，
>
> 讲解RabbitMQ在软件开发的长河还能发挥很大的作用。

### i.『 规则引擎篇 』

- ![](https://james-1258744956.cos.ap-shanghai.myqcloud.com/IOT%20Technical%20Guide/Ruler.png)[《解放放劳动力的Rule Engine》](https://iot.mushuwei.cn/#/rule-engine)

> 你是否还在为繁多的业务而劳累，例如：当温度超过120度，就发出报警。在这章，通过学习规则引擎，
>
> 你将可以端起你手中的咖啡。

- [x] [规则引擎在IoT的重要性？](https://iot.mushuwei.cn/#/what-rule-engine)
- [x] [Easy-Rules规则引擎在IoT的使用](https://iot.mushuwei.cn/#/rule-engine-easyrules)
- [x] [带你走进ThingsBoard规则引擎的实现原理](https://iot.mushuwei.cn/#/rule-engine-thingsboard)
- [x] [阿里云物联网平台规则引擎的面纱](https://iot.mushuwei.cn/#/rule-engine-aliyun)

### j.『 Docker和k8s篇 』

- ![](https://james-1258744956.cos.ap-shanghai.myqcloud.com/IOT%20Technical%20Guide/Docker.png)[《Docker在IoT技术领域的应用》](https://iot.mushuwei.cn/#/docker-iot)

> 容器已经成为今年来最炙手可热的技术了，通过Docker,你将无需为部署环境而烦恼，
>
> 只需关注你的业务开发和逻辑。将极大的减少部署和运维的劳动力。



- ![](https://james-1258744956.cos.ap-shanghai.myqcloud.com/IOT%20Technical%20Guide/kubernetes.png)[《DevOps的领头羊-Kubernetes》](https://iot.mushuwei.cn/#/devops-kubernetes)

> 串联开发团队和IT运维团队！利用K8s让两个团队之间，建立良好的沟通和协作，
>
> 更块更可靠的创建高质量软件！


## IoT在线资源推荐

-  关于物联网框架、开源库、操作系统和平台的资源 https://phodal.github.io/awesome-iot/
- 一个很棒的物联网项目和资源的列表  https://github.com/HQarroum/awesome-iot/



## 号外

  致力于打造专业的物联网技术圈，帮助朋友和同学在物联网的风口上早日起飞 🛫️

主要内容有:
1. :loudspeaker: ThingsBoard源码解析
高达8k+的开源物联网平台，物联网解决方案的设备管理、数据收集、处理和可视化
2. :wind_chime: 应用于物联网应用层技术领域的技术和实践

并且你还可以得到：

- Java通信领域Netty技术的极大提升。
- MQTT, CoAP, Http2和网关协议的理论知识和指导。
- 手把手教你搭建高可用及高性能IoT平台。



## 寻找志同道合的朋友

专业的IoT技术指导：https://t.zsxq.com/BmaYFyF

加我好友: **微信号(jamesmsw)**

免费的物联网技术圈：**加我好友，拉你进群！**

关注微信公众号: **物联网技术指导**


## 版权说明

- ✍️ [穆书伟 (sanshengshui@github)](https://github.com/sanshengshui)
- 除非另行注明，这个项目中的所有内容采用Apache2.0（[Apache-2.0](http://www.apache.org/licenses/LICENSE-2.0)）协议共享。
- 不少文章在原基础上翻译或演绎而来，页面上方标注了原作者、原文链接以及原文采用的协议。如有版权疑问，请在 Issue 中提出。
- 如果引用本此项目教程代码或者文章,请注明作者和github项目地址。
- 欢迎通过 Issue 或者 Pull Request 推荐你认为合适的资料，让这份菜单更充实一些。

:four_leaf_clover::four_leaf_clover::four_leaf_clover::four_leaf_clover::four_leaf_clover::four_leaf_clover::four_leaf_clover:

## 为什么要做这份菜单

> 在学习开源物联网平台ThingsBoard和使用阿里云物联网平台的时候，我发现ThingsBoard的更新速度十分频繁且代码架构十分优秀，随着未来十年内将会有数十亿的设备将联网和国内对物联网领域的高热度。众多的开发人员经历过Web2.0和移动互联网的时代，但是对于未来的设备联网这块的知识十分缺乏，并且搜索引擎上大多数文章都比较的粗浅。此外，这些资料往往只涉及某些特定的话题，如果能有一份菜单将这些菜谱以特定的方式串起来，那么对于 IOT 学习者来说将会是极大的便利。尤其对于我这样热爱查阅社区资料胜过出版物的懒人:new_moon_with_face: 随着我的学习节奏还会不断有新的菜谱加入进来。
>
