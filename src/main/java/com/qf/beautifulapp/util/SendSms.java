package com.qf.beautifulapp.util;

import com.alibaba.fastjson.JSONObject;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.profile.DefaultProfile;
import com.google.gson.Gson;
import java.util.*;
import com.aliyuncs.dysmsapi.model.v20170525.*;

public class SendSms {
    //待会需要将主函数改造为一个对外开放的静态方法
    public static boolean sendcode(String tel,String code) {
        //获取阿里云API认证
        DefaultProfile profile = DefaultProfile.getProfile("cn-hangzhou", "LTAI5tEXhWcSZ7FUxWLp55sN", "5ldrPJx70aLhCQC4MP345kPh25jKmf");
        //创建客户端（本服务器对阿里云而言就是客户端）
        IAcsClient client = new DefaultAcsClient(profile);
        //创建一个发送短信的请求
        SendSmsRequest request = new SendSmsRequest();
        //发送短信的电话号码
        request.setPhoneNumbers(tel);
        //短信的签名[]
        request.setSignName("技术汪");
        //短信的模板
        request.setTemplateCode("SMS_227747205");

        //将code转为json格式
        HashMap<String,String> map = new HashMap<>();
        map.put("code",code);
        //生成请求
        request.setTemplateParam(JSONObject.toJSONString(map));

        try {
            //客户端发送请求
            SendSmsResponse response = client.getAcsResponse(request);
            //将数据转换为json格式
            String s = new Gson().toJson(response);
            JSONObject jsonObject = JSONObject.parseObject(s);
            if(jsonObject.get("code").equals("OK")){
                //说明短信发送成功
                return true;
            }
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }
}
