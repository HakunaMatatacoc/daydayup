package com.api;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.cookie.CookiePolicy;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class weather {

    public static void main(String[] args) {
        HttpClient httpClient = new HttpClient();
        try {
            httpClient.getParams().setCookiePolicy(CookiePolicy.BROWSER_COMPATIBILITY);
            getMessage(httpClient);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    private static void getMessage(HttpClient httpClient) throws IOException {
        /**
         *  URL 这个地址说从高德开发平台是找到，这种API其实网上一大把
         *  说明下 有两个核心参数  city 是城市码  key是高德上申请到
         */
        String dataUrl="https://restapi.amap.com/v3/weather/weatherInfo?city=110101&key=13fbe51c8f015e57f577fe3ab933df84";
        PostMethod postMethod=new PostMethod();
        GetMethod getMethod = new GetMethod(dataUrl);
        httpClient.executeMethod(getMethod);
        String text = getMethod.getResponseBodyAsString();
        JSONObject jsonObject=JSONObject.parseObject(text);
        System.out.println("返回json内容:"+jsonObject.toJSONString());
        //返回到json你可以解析DB 还可以输入其他
        writeContent(jsonObject.toJSONString());

    }
    public  static void  writeContent(String data){
        try{
            File file =new File("weather.txt");
            if(!file.exists()){
                file.createNewFile();
            }
            FileWriter fileWritter = new FileWriter(file.getName(),true);
            BufferedWriter bw= new BufferedWriter(fileWritter);
            bw.write(data);
            bw.newLine();
            bw.flush();
            bw.close();
            System.out.println("Done");
        }catch(IOException e){
            e.printStackTrace();
        }
    }
}
