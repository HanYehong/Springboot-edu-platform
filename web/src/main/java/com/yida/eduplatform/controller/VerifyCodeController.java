package com.yida.eduplatform.controller;

import com.alibaba.fastjson.JSONObject;
import com.yida.eduplatform.service.RedisService;
import com.yida.eduplatform.util.ResponseUtil;
import com.zhenzi.sms.ZhenziSmsClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.Random;
import java.util.concurrent.TimeUnit;

@RestController
public class VerifyCodeController {
    @Autowired
    private ResponseUtil responseUtil;
    private String apiUrl = "http://sms_developer.zhenzikj.com";
    private String appId = "100041";
    private String appSecret = "abf380a0-5f8e-4b45-826c-122fc52a0da5";
    private String mobile;
    private String code;
    private Long createTime;
    @Autowired
    private RedisService redisService;

    @PostMapping("/sendsms/{id}/{phone}")
    public ResponseUtil sendSms(@PathVariable("id")int stuId,@PathVariable("phone") String phone){
        try {
            String mobile = phone;
            System.out.println("mobile:::"+mobile);
            JSONObject json = null;
            String verifyCode = String.valueOf(new Random().nextInt(899999) + 100000);
            System.out.println("验证码:::"+verifyCode);
            ZhenziSmsClient client = new ZhenziSmsClient(apiUrl, appId, appSecret);
            String result = client.send(mobile, "您的验证码为"+verifyCode);
            System.out.println(result);
            json = JSONObject.parseObject(result);
            if(json.getIntValue("code") != 0){
                responseUtil.setSuccess(false);
                responseUtil.setMessage("发送失败，错误码："+json.getIntValue("code"));
                return responseUtil;
            }
            //将验证码存入redis中，设置过期时间为15分钟
            redisService.set(stuId+"",phone+":"+verifyCode,(long)15*60);
            this.mobile = mobile;
            this.code = verifyCode;
            this.createTime = System.currentTimeMillis();
            responseUtil.setSuccess(true);
            responseUtil.setMessage("验证码已发送");
            return responseUtil;
        } catch (Exception e) {
            e.printStackTrace();
        }
        responseUtil.setSuccess(false);
        responseUtil.setMessage("发送失败");
        return responseUtil;
    }

    @PostMapping("/checkcode/{id}/{phone}/{code}")
    public ResponseUtil checkCode(@PathVariable("id")int id,@PathVariable("phone")String phone,@PathVariable("code")String code){
        String redisCode = (String) redisService.get(id+"");
        if(redisCode == null){
            responseUtil.setMessage("验证码已过期");
            responseUtil.setSuccess(false);
            return responseUtil;
        }
        String[] value = redisCode.split(":");
        if(!value[0].equals(phone)){
            responseUtil.setMessage("手机号不正确");
            responseUtil.setSuccess(false);
            return responseUtil;
        }
        if(!value[1].equals(code)){
            responseUtil.setMessage("验证码不正确");
            responseUtil.setSuccess(false);
            return responseUtil;
        }
        responseUtil.setMessage("验证成功");
        responseUtil.setSuccess(true);
        return responseUtil;
    }
}
