<p align="center">
    <img src="https://james-1258744956.cos.ap-shanghai.myqcloud.com/IOT%20Technical%20Guide/logo.png" alt="IOT Technical Guide">
</p>
<p align="center">
    <a href="https://travis-ci.org/IoT-Technology/IOT-Technical-Guide"><img src="https://travis-ci.org/IoT-Technology/IOT-Technical-Guide.svg?branch=master" /></a>
    <a href="https://github.com/sanshengshui/Groza/blob/master/LICENSE"><img src="https://img.shields.io/badge/license-Apache-000000.svg" /></a>
    <a href="https://github.com/sanshengshui/IOT-Technical-Guide/issues"><img src="http://isitmaintained.com/badge/open/dreamans/syncd.svg" /></a>




 **:maple_leaf:高质量的 IOT 技术教程，代码主要源于国外开源物联网平台[ThingsBoard](https://thingsboard.io/)和对阿里云物联网平台的感悟**



备注:  :unlock: :表示**公开浏览**;   :closed_lock_with_key: :表示**需要加入作者知识星球才可浏览**; 


<p align="center">
    <img src="https://james-1258744956.cos.ap-shanghai.myqcloud.com/thingsboard-mqtt-part2/halving_line.jpg" alt="分割线">
</p>
<p align="center">




## 框架一览图

<p align="center">
    <img src="/png/thingsboard-architecture.svg" alt="IoT Architecture">
</p>



## ![](https://james-1258744956.cos.ap-shanghai.myqcloud.com/thingsboard-mqtt-part2/thingsboard_logo_blue.png?imageMogr2/thumbnail/!10p) 源码解析系列

### a.『 准备篇 』

- :unlock: [《ThingsBoard中文官网》](http://www.ithingsboard.com/)<br>

- :unlock: [《物联网时代-Thingsboard源码分析-调试环境调试》](https://blog.mushuwei.cn/2018/07/21/%E7%89%A9%E8%81%94%E7%BD%91%E6%97%B6%E4%BB%A3-Thingsboard%E6%BA%90%E7%A0%81%E5%88%86%E6%9E%90-%E8%B0%83%E8%AF%95%E7%8E%AF%E5%A2%83%E6%90%AD%E5%BB%BA/)<br>

- :unlock: [《物联网时代-Thingsboard源码分析-项目结构说明》](https://blog.mushuwei.cn/2018/07/24/%E7%89%A9%E8%81%94%E7%BD%91%E6%97%B6%E4%BB%A3-ThingsBoard%E6%BA%90%E7%A0%81%E5%88%86%E6%9E%90-%E9%A1%B9%E7%9B%AE%E7%BB%93%E6%9E%84%E8%AF%B4%E6%98%8E/)<br>

### b.『设备连接协议篇 』

- **MQTT** ![](https://james-1258744956.cos.ap-shanghai.myqcloud.com/IOT%20Technical%20Guide/MQTT.png)

 协议 :  [MQTT](http://mqtt.org/)

 技术框架 :  [Netty](https://netty.io/)

- :unlock: [《MQTT入门篇》](https://blog.mushuwei.cn/2020/02/05/mqtt入门篇/)<br>

- :unlock: [《物联网时代-ThingsBoard源码分析-MQTT设备连接协议-上》](https://blog.mushuwei.cn/2020/01/24/物联网时代-ThingsBoard源码分析-MQTT设备连接协议-上/)<br>

- :closed_lock_with_key: [《物联网时代-ThingsBoard源码分析-MQTT设备连接协议-下》](https://blog.mushuwei.cn/2020/04/23/物联网时代-ThingsBoard源码分析-MQTT设备连接协议-下/)<br>

  

- **CoAP**![](https://james-1258744956.cos.ap-shanghai.myqcloud.com/IOT%20Technical%20Guide/coap.png)

  协议 :  [CoAP](https://coap.technology/)

  框架: [Californium(cf)](https://www.eclipse.org/californium/)
  
- :unlock: [《初识CoAP协议》](https://blog.mushuwei.cn/2020/04/30/%E5%88%9D%E8%AF%86CoAP%E5%8D%8F%E8%AE%AE/)<br>

- :unlock: [《抓住CoAP协议的'心'》](https://blog.mushuwei.cn/2020/05/07/%E6%8A%93%E4%BD%8FCoAP%E5%8D%8F%E8%AE%AE%E7%9A%84-%E5%BF%83/)<br> 

- :closed_lock_with_key: [《物联网时代-ThingsBoard源码分析-CoAP设备连接协议》](https://blog.mushuwei.cn/2020/05/13/%E7%89%A9%E8%81%94%E7%BD%91%E6%97%B6%E4%BB%A3-ThingsBoard%E6%BA%90%E7%A0%81%E5%88%86%E6%9E%90-CoAP%E8%AE%BE%E5%A4%87%E8%BF%9E%E6%8E%A5%E5%8D%8F%E8%AE%AE/)<br> 

- :closed_lock_with_key: [《100行代码快速搭建功能完备的CoAP Server服务》](https://blog.mushuwei.cn/2020/05/10/100%E8%A1%8C%E4%BB%A3%E7%A0%81%E5%BF%AB%E9%80%9F%E6%90%AD%E5%BB%BA%E5%8A%9F%E8%83%BD%E5%AE%8C%E5%A4%87%E7%9A%84CoAP-Server%E6%9C%8D%E5%8A%A1/)<br> 

- **HTTP** ![](https://james-1258744956.cos.ap-shanghai.myqcloud.com/IOT%20Technical%20Guide/HTTP.png)
  
  协议 :  [HTTP](https://baike.baidu.com/item/HTTP/243074)
  
  框架 :  [Spring Boot](https://spring.io/projects/spring-boot)
  
  


- **Gateway** ![](https://james-1258744956.cos.ap-shanghai.myqcloud.com/IOT%20Technical%20Guide/gateway.png)
  
  概述 : 网关
  
  *常用协议*
  
  - **Modbus** ![](https://james-1258744956.cos.ap-shanghai.myqcloud.com/gateway/modbus.jpg?imageMogr2/thumbnail/!50p)
  
  - **OPC UA**![](https://james-1258744956.cos.ap-shanghai.myqcloud.com/gateway/opcUA-logo.jpg?imageMogr2/thumbnail/!60p)
  



- **WebSocket**![](https://james-1258744956.cos.ap-shanghai.myqcloud.com/IOT%20Technical%20Guide/Websocket.png)

  概述：WebSocket

  :zap: 待更新......

### c.『数据交换和序列化篇 』

- **JSON**![](https://james-1258744956.cos.ap-shanghai.myqcloud.com/IOT%20Technical%20Guide/language.png)
  
  > 物模型指将物理空间中的实体数字化，并在云端构建该实体的数据模型。物模型TSL（Thing Specification Language）。是一个JSON格式的文件。

  :zap: 待更新......


- **Protocol Buffers**![](https://james-1258744956.cos.ap-shanghai.myqcloud.com/IOT%20Technical%20Guide/buffer.png)

  > ProtoBuf是一种语言无关，与平台无关并且具有可扩展机制，用于序列化结构化数据

  官方网址：https://developers.google.com/protocol-buffers

​	:unlock: [《Proto语言指南(proto3)》](https://blog.mushuwei.cn/2018/10/07/Proto%E8%AF%AD%E8%A8%80%E6%8C%87%E5%8D%97-proto3/)<br>

​	

### d.『设备、接口认证和安全篇 』

- **Spring Security**![](https://james-1258744956.cos.ap-shanghai.myqcloud.com/IOT%20Technical%20Guide/security.png)

  

- **OAuth2**![](https://james-1258744956.cos.ap-shanghai.myqcloud.com/IOT%20Technical%20Guide/oauth-2-sm.png?imageMogr2/thumbnail/!50p)

:zap: 待更新......

### e.『流处理和消息队列篇 』	

- **kafka**![](https://james-1258744956.cos.ap-shanghai.myqcloud.com/IOT%20Technical%20Guide/kafka.png)



- **RabbitMQ**![](https://james-1258744956.cos.ap-shanghai.myqcloud.com/IOT%20Technical%20Guide/RabbitMQ-logo.svg)

:zap: 待更新......

### f.『规则引擎篇 』

**Rule Engine**![](https://james-1258744956.cos.ap-shanghai.myqcloud.com/IOT%20Technical%20Guide/Ruler.png)

:zap: 待更新......

### g.『Docker和Kubernetes篇 』

- **Docker**![](https://james-1258744956.cos.ap-shanghai.myqcloud.com/IOT%20Technical%20Guide/Docker.png)



- **Kubernetes**![](https://james-1258744956.cos.ap-shanghai.myqcloud.com/IOT%20Technical%20Guide/kubernetes.png)

:zap: 待更新......

## IoT在线资源推荐

-  关于物联网框架、开源库、操作系统和平台的资源 https://phodal.github.io/awesome-iot/
- 一个很棒的物联网项目和资源的列表  https://github.com/HQarroum/awesome-iot/



## 号外

  致力于打造专业的物联网技术圈，帮助朋友和同学在物联网的风口上早日起飞 🛫️

主要内容有:
1. :loudspeaker: ThingsBoard源码解析
高达5k+的开源物联网平台，物联网解决方案的设备管理、数据收集、处理和可视化
2. :wind_chime: 应用于物联网应用层技术领域的技术和实践

并且你还可以得到：

- Java通信领域Netty技术的极大提升。
- MQTT, CoAP, Http2和网关协议的理论知识和指导。
- 手把手教你搭建高可用及高性能IoT平台。

<p align="center">
    <img src="/png/chat.jpg" width="365" height="475" alt="物联网技术指导知识星球">
    <img src="/png/Wechat.jpg" width="337" height="448" alt="联系方式">
</p>






## 版权说明

- ✍️ [穆书伟 (sanshengshui@github)](https://github.com/sanshengshui)
- 除非另行注明，这个项目中的所有内容采用Apache2.0（[Apache-2.0](http://www.apache.org/licenses/LICENSE-2.0)）协议共享。
- 不少文章在原基础上翻译或演绎而来，页面上方标注了原作者、原文链接以及原文采用的协议。如有版权疑问，请在 Issue 中提出。
- 如果引用本此项目教程代码或者文章,请注明作者和github项目地址。
- 欢迎通过 Issue 或者 Pull Request 推荐你认为合适的资料，让这份菜单更充实一些。

:four_leaf_clover::four_leaf_clover::four_leaf_clover::four_leaf_clover::four_leaf_clover::four_leaf_clover::four_leaf_clover:

## 为什么要做这份菜单

> 在学习开源物联网平台ThingsBoard和使用阿里云物联网平台的时候，让我对物联网这个领域产生了极大的兴趣。我发现ThingsBoard的更新速度十分频繁且代码架构十分优秀，随着未来十年内将会有数十亿的设备将联网和国内对物联网领域的高热度。众多的开发人员经历过Web2.0和移动互联网的时代，但是对于未来的设备联网这块的知识十分缺乏，并且搜索引擎上大多数文章都比较的粗浅。此外，这些资料往往只涉及某些特定的话题，如果能有一份菜单将这些菜谱以特定的方式串起来，那么对于 IOT 学习者来说将会是极大的便利。尤其对于我这样热爱查阅社区资料胜过出版物的懒人:new_moon_with_face: 随着我的学习节奏还会不断有新的菜谱加入进来。
>
