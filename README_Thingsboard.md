<p align="center">
    <a href="https://github.com/IoT-Technology/IOT-Technical-Guide/commits/" title="Last Commit"><img src="https://img.shields.io/github/last-commit/IoT-Technology/IOT-Technical-Guide?style=flat"></a>
    <a href="https://travis-ci.org/IoT-Technology/IOT-Technical-Guide" title="Travis CI"><img src="https://travis-ci.org/IoT-Technology/IOT-Technical-Guide.svg?branch=master"/></a>
    <a href="https://github.com/IoT-Technology/IOT-Technical-Guide/blob/master/LICENSE" title="License"><img src="https://img.shields.io/badge/License-Apache%202.0-green.svg?style=flat"></a>
    <a href="https://github.com/IoT-Technology/IOT-Technical-Guide/issues" title="Open Issues"><img src="https://img.shields.io/github/issues/IoT-Technology/IOT-Technical-Guide?style=flat"></a>
</p>
<p align="center">
    <a href="https://blog.mushuwei.cn/categories/IOT-Technical-Guide/"><img src="https://img.shields.io/badge/阅读-read-brightgreen.svg" alt="阅读">
    <a href="#投稿"><img src="https://img.shields.io/badge/support-投稿-critical.svg" alt="投稿"></a>
    <a href="https://github.com/IoT-Technology/IOT-Technical-Guide/stargazers" title="Stars"><img src="https://img.shields.io/github/stars/IoT-Technology/IOT-Technical-Guide.svg?style=social&label=Star"></a>
    <a href="https://github.com/IoT-Technology/IOT-Technical-Guide/network/members" title="Fork Members"><img src="https://img.shields.io/github/forks/IoT-Technology/IOT-Technical-Guide.svg?style=social&label=Fork"></a>
    <a href="https://github.com/IoT-Technology/IOT-Technical-Guide/watchers" title="Watchers"><img src="https://img.shields.io/github/watchers/IoT-Technology/IOT-Technical-Guide.svg?style=social&label=Watch"></a>
</p>


 **:maple_leaf:高质量的 IOT 技术教程，代码主要源于国外开源物联网平台[ThingsBoard](https://thingsboard.io/)和对阿里云物联网平台的感悟**





<p align="center">
    <img src="https://james-1258744956.cos.ap-shanghai.myqcloud.com/thingsboard-mqtt-part2/halving_line.jpg" alt="分割线">
</p>
<p align="center">

[📖 从0搭建高性能IoT平台和实践](README.md) | 📖 Thingsboard源码分析


## ![](https://james-1258744956.cos.ap-shanghai.myqcloud.com/thingsboard-mqtt-part2/thingsboard_logo_blue.png?imageMogr2/thumbnail/!10p) 源码解析系列

### a.『 准备篇 』

-  [《ThingsBoard中文官网》](http://www.ithingsboard.com/)<br>
-  [《物联网时代-Thingsboard源码分析-调试环境调试》](https://iot.mushuwei.cn/#/thingsboard/thingsboard-build)<br>
-  [《物联网时代-Thingsboard源码分析-项目结构说明》](https://iot.mushuwei.cn/#/thingsboard/project-structure)<br>



### b.『设备连接协议篇 』

- **MQTT** 

 协议 :  [MQTT](http://mqtt.org/)

 技术框架 :  [Netty](https://netty.io/)

  -  [《物联网时代-ThingsBoard源码分析-MQTT设备连接协议-上》](https://iot.mushuwei.cn/#/thingsboard/mqtt-protocol-part1)<br>

  -  [《物联网时代-ThingsBoard源码分析-MQTT设备连接协议-下》](https://iot.mushuwei.cn/#/thingsboard/mqtt-protocol-part2)<br>



- **CoAP**

  协议 :  [CoAP](https://coap.technology/)

  框架: [Californium(cf)](https://www.eclipse.org/californium/)
  
-  [《初识CoAP并抓住它的"心"》](https://iot.mushuwei.cn/#/coap-basic)<br>
  
-  [《物联网时代-ThingsBoard源码分析-CoAP设备连接协议》](https://iot.mushuwei.cn/#/thingsboard/coap-protocol)<br>




- **HTTP** 
  
  协议 :  [HTTP](https://baike.baidu.com/item/HTTP/243074)
  
  框架 :  [Spring Boot](https://spring.io/projects/spring-boot)
  
-  [《物联网时代-ThingsBoard源码分析-HTTP设备连接协议》](https://iot.mushuwei.cn/#/thingsboard/http-protocol)<br>



### c.『网关篇 』

- **Gateway** ![](https://james-1258744956.cos.ap-shanghai.myqcloud.com/IOT%20Technical%20Guide/gateway.png)

  概述 : 网关

  *常用协议*

  - **Modbus** 

- :unlock: [《一文说透Modbus协议》](https://blog.mushuwei.cn/2020/05/23/%E4%B8%80%E6%96%87%E8%AE%B2%E9%80%8FModbus%E5%8D%8F%E8%AE%AE/)<br>


  - **OPC UA**

  

### d.『存储和数据库篇 』

-  [《数据模型之用户相关表结构设计》](https://iot.mushuwei.cn/#/thingsboard/user-table)<br>
-  [《数据模型之设备相关表结构设计》](https://iot.mushuwei.cn/#/thingsboard/device-table)<br>
-  [《数据模型之规则引擎相关表结构设计》](https://iot.mushuwei.cn/#/thingsboard/rulengine-table)<br>
- [《领略Spring Data JPA在Thingsboard中的使用》](https://iot.mushuwei.cn/#/thingsboard/jpa-sql)<br>

  


### e.『数据交换和序列化篇 』

- **JSON**
  
  > 物模型指将物理空间中的实体数字化，并在云端构建该实体的数据模型。物模型TSL（Thing Specification Language）。是一个JSON格式的文件。

	名词解释 : [物模型](https://www.alibabacloud.com/help/zh/doc-detail/73727.htm)

  :closed_lock_with_key: [《技术魔法剖析物模型》](https://blog.mushuwei.cn/2019/04/17/%E6%88%91%E6%98%AF%E5%BA%96%E4%B8%81-%E8%82%A2%E8%A7%A3IOT%E5%B9%B3%E5%8F%B0%E4%B9%8B%E7%89%A9%E6%A8%A1%E5%9E%8B/)<br>




- **Protocol Buffers**

  > ProtoBuf是一种语言无关，与平台无关并且具有可扩展机制，用于序列化结构化数据

  官方网址：https://developers.google.com/protocol-buffers

	:unlock: [《Proto语言指南(proto3)》](https://blog.mushuwei.cn/2018/10/07/Proto%E8%AF%AD%E8%A8%80%E6%8C%87%E5%8D%97-proto3/)<br>

	

### f.『设备、接口认证和安全篇 』

- **Spring Security**

  

- **OAuth2**

:zap: 待更新......

### g.『流处理和消息队列篇 』	

- **kafka**



- **RabbitMQ**

:zap: 待更新......

### h.『规则引擎篇 』

**Rule Engine**

:zap: 待更新......

### i.『Docker和Kubernetes篇 』

- **Docker**



- **Kubernetes**

:zap: 待更新......

## IoT在线资源推荐

-  关于物联网框架、开源库、操作系统和平台的资源 https://phodal.github.io/awesome-iot/
- 一个很棒的物联网项目和资源的列表  https://github.com/HQarroum/awesome-iot/



## 号外

  致力于打造专业的物联网技术圈，帮助朋友和同学在物联网的风口上早日起飞 🛫️

主要内容有:
1. :loudspeaker: ThingsBoard源码解析
高达7k+的开源物联网平台，物联网解决方案的设备管理、数据收集、处理和可视化
2. :wind_chime: 应用于物联网应用层技术领域的技术和实践

并且你还可以得到：

- Java通信领域Netty技术的极大提升。
- MQTT, CoAP, Http2和网关协议的理论知识和指导。
- 手把手教你搭建高可用及高性能IoT平台。



## 寻找志同道合的朋友

专业的IoT技术指导：https://t.zsxq.com/BmaYFyF

加我好友: **微信号(jamesmsw)**

免费的物联网技术圈：**加我好友，拉你进群！**




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
