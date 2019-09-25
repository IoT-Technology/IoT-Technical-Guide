<p align="center">
    <img src="https://james-1258744956.cos.ap-shanghai.myqcloud.com/IOT%20Technical%20Guide/logo.png" alt="IOT Technical Guide">
</p>
<p align="center">
    <a href="https://travis-ci.org/sanshengshui/IOT-Technical-Guide"><img src="https://travis-ci.org/sanshengshui/IOT-Technical-Guide.svg?branch=master" /></a>
    <a href="https://github.com/sanshengshui/Groza/blob/master/LICENSE"><img src="https://img.shields.io/badge/license-Apache-000000.svg" /></a>
    <a href="https://github.com/sanshengshui/IOT-Technical-Guide/issues"><img src="http://isitmaintained.com/badge/open/dreamans/syncd.svg" /></a>




 **:maple_leaf:高质量的 IOT 技术教程，代码主要源于国外开源物联网平台[ThingsBoard](https://thingsboard.io/)和对阿里云物联网平台的感悟**

### ![](https://james-1258744956.cos.ap-shanghai.myqcloud.com/IOT%20Technical%20Guide/IOT.png)IOT

- [开源且架构优秀的物联网平台-Thingsboard](https://thingsboard.io/)

- [品宣和产品的保证-阿里云物联网平台](https://www.aliyun.com/product/iot-deviceconnect?spm=5176.12825654.eofdhaal5.103.e9392c4adOqibP)


### :couple: 商业前景

- [物联网前景与市场](https://blog.mushuwei.cn/2018/10/26/IOT市场及技术模拟/)

### :white_check_mark:准备工作

> - 非必须工作，如若编译不成功，可以参考[Issues](https://github.com/thingsboard/thingsboard/issues)。只是为了更好的让大家了解thingsboard,通过调试代码的方式，得到更直观的方法调用。
>
> - [安装教程](https://thingsboard.io/docs/installation/)，当然也可以直接使用官方提供的[Demo](http://demo.thingsboard.io)

- ​	[物联网时代-Thingsboard源码分析-调试环境搭建](https://blog.mushuwei.cn/2018/07/21/%E7%89%A9%E8%81%94%E7%BD%91%E6%97%B6%E4%BB%A3-Thingsboard%E6%BA%90%E7%A0%81%E5%88%86%E6%9E%90-%E8%B0%83%E8%AF%95%E7%8E%AF%E5%A2%83%E6%90%AD%E5%BB%BA/)

### :scroll: 概念

- 物模型![](https://james-1258744956.cos.ap-shanghai.myqcloud.com/IOT%20Technical%20Guide/language.png)
  
  > 此处引用阿里云物联网平台的解释，此处感谢:clap: ​[Alibaba Cloud@github](https://github.com/aliyun)
  
  释义:  [什么是物模型](https://help.aliyun.com/document_detail/73727.html)
  
  博文: [物联网时代　跟着Thingsboard学IOT架构-物模型](https://blog.mushuwei.cn/2019/04/17/%E6%88%91%E6%98%AF%E5%BA%96%E4%B8%81-%E8%82%A2%E8%A7%A3IOT%E5%B9%B3%E5%8F%B0%E4%B9%8B%E7%89%A9%E6%A8%A1%E5%9E%8B/)

### :email: 协议
- ![](https://james-1258744956.cos.ap-shanghai.myqcloud.com/IOT%20Technical%20Guide/coap.png)

  协议 :  [CoAP](https://coap.technology/)

  技术框架 : [Californium(cf)](https://www.eclipse.org/californium/)
  
  教程 : [IOT-Guide-Coap](https://github.com/sanshengshui/IOT-Technical-Guide/tree/master/IOT-Guide-Coap)
  
  博文 : [物联网时代 跟着Thingsboard学IOT架构-CoAP设备协议](https://blog.mushuwei.cn/2019/07/25/%E7%89%A9%E8%81%94%E7%BD%91%E6%97%B6%E4%BB%A3-%E8%B7%9F%E7%9D%80Thingsboard%E5%AD%A6IOT%E6%9E%B6%E6%9E%84-CoAP%E8%AE%BE%E5%A4%87%E5%8D%8F%E8%AE%AE/)

- ![](https://james-1258744956.cos.ap-shanghai.myqcloud.com/IOT%20Technical%20Guide/MQTT.png)

   协议 :  [MQTT](http://mqtt.org/)
  
   技术框架 :  [Netty](https://netty.io/)
   
   教程 : [IOT-Guide-MQTT](https://github.com/sanshengshui/IOT-Technical-Guide/tree/master/IOT-Guide-MQTT)
   
   博文 : [物联网时代-跟着Thingsboard学IOT架构-MQTT设备协议](https://blog.mushuwei.cn/2019/07/24/%E7%89%A9%E8%81%94%E7%BD%91%E6%97%B6%E4%BB%A3-%E8%B7%9F%E7%9D%80Thingsboard%E5%AD%A6IOT%E6%9E%B6%E6%9E%84-MQTT%E8%AE%BE%E5%A4%87%E5%8D%8F%E8%AE%AE/)

- ![](https://james-1258744956.cos.ap-shanghai.myqcloud.com/IOT%20Technical%20Guide/HTTP.png)
  
   协议 :  [HTTP](https://en.wikipedia.org/wiki/Hypertext_Transfer_Protocol)
   
   技术框架 :  [Spring Boot](https://spring.io/projects/spring-boot)
       
   教程 : [IOT-Guide-HTTP](https://github.com/sanshengshui/IOT-Technical-Guide/tree/master/IOT-Guide-HTTP)
   
   博文 : [物联网时代-跟着Thingsboard学IOT架构-HTTP设备协议及API相关限制](https://blog.mushuwei.cn/2019/08/16/%E7%89%A9%E8%81%94%E7%BD%91%E6%97%B6%E4%BB%A3-%E8%B7%9F%E7%9D%80Thingsboard%E5%AD%A6IOT%E6%9E%B6%E6%9E%84-HTTP%E8%AE%BE%E5%A4%87%E5%8D%8F%E8%AE%AE%E5%8F%8AAPI%E7%9B%B8%E5%85%B3%E9%99%90%E5%88%B6/)
   
- ![](https://james-1258744956.cos.ap-shanghai.myqcloud.com/IOT%20Technical%20Guide/gateway.png)
  
    释义 : [Gateway](https://baike.baidu.com/item/%E7%BD%91%E5%85%B3/98992?fr=aladdin)
    
    教程 : [IOT-Guide_Gateway](https://github.com/sanshengshui/IOT-Technical-Guide/tree/master/IOT-Guide-Gateway)
    
    博文 : 

#### :closed_lock_with_key:  安全框架

- [Spring Security + JWT]() [<img src="https://james-1258744956.cos.ap-shanghai.myqcloud.com/IOT%20Technical%20Guide/pic_logo.svg" align="center" width="32">](https://jwt.io/)
- [OAuth2.0]() [<img src="https://james-1258744956.cos.ap-shanghai.myqcloud.com/IOT%20Technical%20Guide/oauth-2-sm.png" align="center" width="32">](https://oauth.net/2/)

### :bar_chart: 物联网通用平台

- [RuleEngine]()
- [DB]()
- [RealTime]()

### :house: 架构

- [Msa]()

  1. 注册发现

     **Eureka** [<img src="png/eureka.png" align="center" width="64">](https://electronjs.org)

     **Consul**

  2. 网关

     **Zuul**

  3. 配置中心

     **Apollo**

  4. 调用链监控

     **CAT**

     **SkyWalking**

  5. 容错限流

     **Hystrix**

  6. 监控报警

     **Prometheus**


###  :wheel_of_dharma: 持续集成->持续部署->持续交付
>DevOps的本质其实是一种鼓励协作的文化
>Dev的工作是,为软件增加新功能和修复缺陷，这要通过频繁的变更来达到,Ops的工作是,保证系统的高稳定性和高性能，这代表着变更越少越不容易出错.
>DevOps是一组技术,包括自动化运维,持续交付,高频部署,Docker,Kubernates等

- [Docker]()
- [Kubernates]()

:honeybee::honeybee::honeybee::honeybee::honeybee::honeybee:

### Project Gantt

![gantt_en](https://james-1258744956.cos.ap-shanghai.myqcloud.com/IOT%20Technical%20Guide/gantt_cn.svg)

:four_leaf_clover::four_leaf_clover::four_leaf_clover::four_leaf_clover::four_leaf_clover::four_leaf_clover::four_leaf_clover:


### 版权说明

- ✍️ [穆书伟 (sanshengshui@github)](https://github.com/sanshengshui)
- 除非另行注明，这个项目中的所有内容采用Apache2.0（[Apache-2.0](http://www.apache.org/licenses/LICENSE-2.0)）协议共享。
- 不少文章在原基础上翻译或演绎而来，页面上方标注了原作者、原文链接以及原文采用的协议。如有版权疑问，请在 Issue 中提出。
- 如果引用本此项目教程代码或者文章,请注明作者和github项目地址。
- 欢迎通过 Issue 或者 Pull Request 推荐你认为合适的资料，让这份菜单更充实一些。

:four_leaf_clover::four_leaf_clover::four_leaf_clover::four_leaf_clover::four_leaf_clover::four_leaf_clover::four_leaf_clover:

### 为什么要做这份菜单

> 在学习开源物联网平台ThingsBoard和使用阿里云物联网平台的时候，让我对物联网这个领域产生了极大的兴趣。我发现ThingsBoard的更新速度十分频繁且代码架构十分优秀，随着未来十年内将会有数十亿的设备将联网和国内对物联网领域的高热度。众多的开发人员经历过Web2.0和移动互联网的时代，但是对于未来的设备联网这块的知识十分缺乏，并且搜索引擎上大多数文章都比较的粗浅。此外，这些资料往往只涉及某些特定的话题，如果能有一份菜单将这些菜谱以特定的方式串起来，那么对于 IOT 学习者来说将会是极大的便利。尤其对于我这样热爱查阅社区资料胜过出版物的懒人:new_moon_with_face: 随着我的学习节奏还会不断有新的菜谱加入进来。
>
