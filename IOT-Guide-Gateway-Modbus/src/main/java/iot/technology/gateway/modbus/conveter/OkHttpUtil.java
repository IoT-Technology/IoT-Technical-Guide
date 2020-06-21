package iot.technology.gateway.modbus.conveter;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.util.HashMap;
import java.util.Map;

/**
 * @author james mu
 * @date 2020/6/21 21:13
 */
@Slf4j
public class OkHttpUtil {

    /**
     * Get请求
     * @param url   URL地址
     * @return  返回结果
     */
    public static String get(String url){
        String result=null;
        try {
            OkHttpClient okHttpClient=new OkHttpClient();
            Request request = new Request.Builder().url(url).build();
            Response response = okHttpClient.newCall(request).execute();
            result=response.body().string();
            log.info("Get请求返回：{}",result);
            return result;
        }catch (Exception e){
            log.error("OkHttp[Get]请求异常",e);
            return result;
        }
    }

    /**
     * Post请求
     * @param url       URL地址
     * @param params    参数
     * @return  返回结果
     */
    public static String post(String url, Map<String,String> params){
        String result=null;
        if (params==null){
            params=new HashMap<String, String>();
        }
        try {
            OkHttpClient okHttpClient=new OkHttpClient();
            FormBody.Builder formBodyBuilder = new FormBody.Builder();
            //添加参数
            log.info("params：{}", JSON.toJSONString(params));
            for (Map.Entry<String,String> map:params.entrySet()){
                String key=map.getKey();
                String value;
                if (map.getValue()==null){
                    value="";
                }else{
                    value=map.getValue();
                }
                formBodyBuilder.add(key,value);
            }
            FormBody formBody =formBodyBuilder.build();
            Request request = new Request.Builder().url(url).post(formBody).build();
            Response response = okHttpClient.newCall(request).execute();
            result=response.body().string();
            log.info("Post请求返回：{}",result);
            return result;
        }catch (Exception e){
            log.error("OkHttp[Post]请求异常",e);
            return result;
        }
    }
}
