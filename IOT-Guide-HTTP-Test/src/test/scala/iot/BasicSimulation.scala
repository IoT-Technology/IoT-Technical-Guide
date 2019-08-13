package iot

import io.gatling.http.Predef._
import io.gatling.core.Predef._
import io.gatling.core.scenario.Simulation

class BasicSimulation extends Simulation{

  //设置请求的根路径
  val httpConf = http.baseUrl("http://127.0.0.1:8080/api/v1")

  var sentHeaders = Map("Content-Type" -> "application/json")

  val scn = scenario("IOT Guide HTTP Test")
    .exec(http("http test")   //http 请求name
      .post("/attributes")     //post url
      .headers(sentHeaders)  //设置body数据格式
      //将json参数用StringBody包起,并作为参数传递给function body()
      .body(StringBody("{\"stringKey\":\"value1\", \"booleanKey\":true, \"doubleKey\":42.0, \"longkey\":73}")))
  setUp(scn.inject(atOnceUsers(10))).protocols(httpConf)
}