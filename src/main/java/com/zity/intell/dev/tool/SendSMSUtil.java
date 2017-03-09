package com.zity.intell.dev.tool;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import com.aliyuncs.sms.model.v20160927.SingleSendSmsRequest;
import com.aliyuncs.sms.model.v20160927.SingleSendSmsResponse;
import org.springframework.stereotype.Component;

import java.util.Random;

/**
 * Created by hanlijie on 2017/2/14.
 */
@Component
public class SendSMSUtil {
    static int max = 9999;
    static int min = 1000;
    static Random random = new Random();
    static int code = random.nextInt(max) % (max - min + 1) + min;

    private String product = "紫藤";
    //private String item = "兑话费";
    private String item = "电影票兑换";

    private String signName = "紫藤科技"; // 阿里云短信签名
    private String templateCode = "SMS_31375049"; // 阿里云短信模板Code
    private String paramString = "{\"code\":\"" + code + "\",\"product\":\""
            + product + "\",\"item\":\"" + item + "\"}"; // 阿里云短信模板参数字符串
    private String recNum; // 发送短信手机号

    /**
     * @description 发送短信
     * @return 发送成功返回 true,否则返回 false
     */
    public int sendSMS(String t) throws Exception{
        recNum = t;
        IClientProfile profile = DefaultProfile.getProfile("cn-tianjin",
                "LTAIU6RdM216vBEV", "sBhOaJ74Ty5N4uiF2L5CwOSx68xOdo");
        DefaultProfile.addEndpoint("cn-tianjin", "cn-tianjin", "Sms",
                "sms.aliyuncs.com");
        IAcsClient client = new DefaultAcsClient(profile);
        SingleSendSmsRequest request = new SingleSendSmsRequest();

        request.setSignName(signName);
        request.setTemplateCode(templateCode);
        request.setParamString(paramString);
        request.setRecNum(recNum);
        SingleSendSmsResponse response = client.getAcsResponse(request);// 短信发送

        /*try {
            IClientProfile profile = DefaultProfile.getProfile("cn-tianjin",
                    "LTAIU6RdM216vBEV", "sBhOaJ74Ty5N4uiF2L5CwOSx68xOdo");
            DefaultProfile.addEndpoint("cn-tianjin", "cn-tianjin", "Sms",
                    "sms.aliyuncs.com");
            IAcsClient client = new DefaultAcsClient(profile);
            SingleSendSmsRequest request = new SingleSendSmsRequest();

            request.setSignName(signName);
            request.setTemplateCode(templateCode);
            request.setParamString(paramString);
            request.setRecNum(recNum);
            SingleSendSmsResponse response = client.getAcsResponse(request);// 短信发送

        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }*/

        return code;
    }
}
