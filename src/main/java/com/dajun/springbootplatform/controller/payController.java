package com.dajun.springbootplatform.controller;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.request.AlipayTradePagePayRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.*;
@Controller
public class payController {
    /*APPID*/
//    @Value("${alipay.appId}")
//    private String appId;
//    /*应用私钥*/
//    @Value("${alipay.appPrivateKey}")
//    private String appPrivateKey;
//    /*支付宝公钥*/
//    @Value("${alipay.alipayPublicKey}")
//    private String  alipayPublicKey;
//    /*支付宝异步通知路径*/
//    @Value("${alipay.notifyUrl}")
//    private String notifyUrl;
    private String appId="2021000121670025";
    /*应用私钥*/
    private String appPrivateKey="MIIEvAIBADANBgkqhkiG9w0BAQEFAASCBKYwggSiAgEAAoIBAQCcqmaXiuYNmQHUzhg1SAj6s1pADQjWDj1yLpRHePgKmTLg37sm+yxvGaZSHXIwRaAD/kcJ3PL99UA4QFRJ3CjICFUCHGqO22r8SfN85gr/KrTcBjrOSwC6RvlWzatS8/oU1L0UEhEvOZAlHVzv/ukI+BaOM2NuoRpMseYxK0Hg9q31Qs5v3YFHsEIhARoqsi/cpI4wSWhFhh7z2lYP98IEEfuR9inXw0TUBIZusJBO63Vh2BEP6VlHltBRU9OEBCN/F/TzQNahWr0JmijM8+smp2hZwIk1r89nNrL5yrbGFdtRkB4/5ZW1cQnsqt94ZKJTRThmx0JYb7vg//LFHmONAgMBAAECggEAWiNksa52HL67IgCORyGyIOROeenF+rrouah5IgJE3Hw2nNG9MXl0oFumqTpN994nagTERLV+jYWtNHA2zuRABo9cscE9wgIWtR+rW2JUU2nz82AWN5lJyuOJFFtzRmAftBqOy1x1zLEW8XPKhD8rP0DWYWzJud4xbo0wv3t/BPqgm4g71+4zay7cquywxlw6uDsGBZMhCjZna811Vop/V5dEjV0IeFXKo3Cd7paWBEf0G8EsaWvCKW0CFgyQQIpT74ALrEL3ploweY9yID8ISSYSHQjqsDTQyT8qudhaOHji8iXxoyN+Jr2PgHyZKg6ObaJWvCerh8dn8HPScWsfAQKBgQDlWNbXFcqs8Ikk2cq0oZlqjN0immrPjHgcvIQ3VUoBnfZW46AxmppaSf3MhFM48HLQwgqwtvsLumKDF9VYZrQUMl0gqWv8CEZ4l+MFrS5Sn9DkdhSoLpNRY8B95qFo38sB078PfJ8zYRhbg3Isnbcrsw16N0YEkDa1Me1vnRKjwQKBgQCu30MsmS3EP1kVdV6ol47F62cNgqX2uUMdvrM53Zi9ZAGH9VxANWegyxzNWf7pwin3n8J1TTRsrcOcd7Zr+w6tN6t8CJwy3mzQKt0qZACMraf3ezurTdgt8P7XB+k/clNTkVKeA587dbQjB1PUo7pP4O8TYTgmqy3VKF6er1nCzQKBgD7+nNwtJyNDWlgd3jipHmuOz3qesXHaom86NJLCQXaaPbx1/11Amt7r7eNMAVPlb5P5iFgrml8JTd+2HyqN4y6c+slq37c1Sq37Aidf/xZAwW7PJDNhv6MeZBPw0irtNWSGFbeaQlwpcjhIrYalt1cwciY8omXEetJgKVh760/BAoGADlLLNcP2M7Tcnx1V+4kucJevgTPKVEModC9CjuLPt/ORDInUXI4DRvBcE5Xjg1IcDeRQETuZp60z8IT9tCqEMTv2F9q8sI33a7WXF/nI8bznnSXC5W0i3+B1fzduq3u5x4aHtdmmGUCy2IupGimvAQvziSrVZpVu057S4TyLpaECgYBM59Mq2JXMd6M+sf+RBV8z87ZomfPxzitedSpZwHp875wjjPhbb+6gpoNIX26CEq/dOXtca8d3w0yJOw3GvNtxLHrYZE2fZ6ECEeeO1sX+BICyCRkKtUVlEmaHyWXomdPDiFhoMje1RZMtb0EM+pH4dnhtYCUWUdGUpDZf+5BEcg==";
    /*支付宝公钥*/
    private String  alipayPublicKey="MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAlmPGK0yAMDEElntmciZSoXixStl3hdd5mS8He+yAFawfxDi2STahEmzXGCIZkLucTWPVmELgo1HgJ9e7QRaF3f0jKuLROtbnj4UlA9Mtlvw1P3Yc06mCU9ZAsYVpYzUfbV+Lq3kdMcEltr9oswx3I6y43mhM78OgcgRXDEWB0G109QCtJ/Q6WqbADLopMqdnh35znP6afWbtPvX6eXn0keSz11vww1D4rbPN3QxLuvmmxpDVZIde2jqf+HCUlujicfqN/MMaCikElKWcVmxc0ETeA5q1pKk1UJ2C2fURUaEZu6ymxYQWcrlHoKKsaNMwIYgfdzuaCxCs701IwB6/rQIDAQAB" ;
    /*支付宝异步通知路径*/
    private String notifyUrl="http://atfbyq.natappfree.cc/notifyUrl";
    // 字符编码
    private final String charSet = "UTF-8";
    //沙箱接口路径
    private final String gatewayUrl ="https://openapi.alipaydev.com/gateway.do";
    //格式
    private final String format = "JSON";
    //签名方式
    private final String signType = "RSA2";
    //支付宝同步通知路径                          这里要写你的
    private final String returnUrl = "http://localhost:8080/returnUrl";

    @ResponseBody
    @GetMapping("/alipay")
    public String alipay(@RequestParam("money") float  money,@RequestParam("seedName") String seedName) throws AlipayApiException {

//        UUID生成订单号
        String currenTime = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
        String orderNumber = UUID.randomUUID().toString().replace("-","").toUpperCase();


//        订单号
        String Order = currenTime+orderNumber;

        //调用封装好的方法（给支付宝接口发送请求）
        return sendRequestToAlipay(Order,money,seedName);
    }

    //支付宝官方提供的接口
    private String sendRequestToAlipay(String outTradeNo,Float totalAmount,String subject) throws AlipayApiException {
        //获得初始化的AlipayClient
        AlipayClient alipayClient = new DefaultAlipayClient(gatewayUrl,appId,appPrivateKey,format,charSet,alipayPublicKey,signType);

        //设置请求参数
        AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();
        alipayRequest.setReturnUrl(returnUrl);
        alipayRequest.setNotifyUrl(notifyUrl);

        //商品描述（可空）
        String body="";
        alipayRequest.setBizContent("{\"out_trade_no\":\"" + outTradeNo + "\","
                + "\"total_amount\":\"" + totalAmount + "\","
                + "\"subject\":\"" + subject + "\","
                + "\"body\":\"" + body + "\","
                + "\"product_code\":\"FAST_INSTANT_TRADE_PAY\"}");

        //返回数据
        return alipayClient.pageExecute(alipayRequest).getBody();

    }

    @RequestMapping("/returnUrl")
    public String returnUrlMethod(HttpServletRequest request) throws AlipayApiException, UnsupportedEncodingException {
        // 获取支付宝GET过来反馈信息
        System.out.println("------------------------同步回调----------------------------------");
        Map<String, String> params = new HashMap<String, String>();
        Map<String, String[]> requestParams = request.getParameterMap();
        for (Iterator<String> iter = requestParams.keySet().iterator(); iter.hasNext();) {
            String name = (String) iter.next();
            String[] values = (String[]) requestParams.get(name);
            String valueStr = "";
            for (int i = 0; i < values.length; i++) {
                valueStr = (i == values.length - 1) ? valueStr + values[i] : valueStr + values[i] + ",";
            }
            // 乱码解决，这段代码在出现乱码时使用
            valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");
            params.put(name, valueStr);
        }

        System.out.println(params);//查看参数都有哪些
        //验证签名（支付宝公钥）
        boolean signVerified = AlipaySignature.rsaCheckV1(params, alipayPublicKey, charSet, signType); // 调用SDK验证签名
        //验证签名通过
        if(signVerified){
            // 商户订单号
            String out_trade_no = new String(request.getParameter("out_trade_no").getBytes("ISO-8859-1"), "UTF-8");

            // 支付宝交易流水号
            String trade_no = new String(request.getParameter("trade_no").getBytes("ISO-8859-1"), "UTF-8");

            // 付款金额
            float money = Float.parseFloat(new String(request.getParameter("total_amount").getBytes("ISO-8859-1"), "UTF-8"));

            System.out.println("商户订单号="+out_trade_no);
            System.out.println("支付宝交易号="+trade_no);
            System.out.println("付款金额="+money);

            /**
             * 这里可以对数据进行crud操作
             */

            //跳转到提示页面（成功或者失败的提示页面）
            return "paySuccess";
        }else{
            return "payError";
        }
    }

    @PostMapping("/notifyUrl")
    public String notifyUrlethod(HttpServletRequest request) throws AlipayApiException, UnsupportedEncodingException {
        // 获取支付宝GET过来反馈信息
        System.out.println("------------------------异步回调----------------------------------");
        Map<String, String> params = new HashMap<String, String>();
        Map<String, String[]> requestParams = request.getParameterMap();
        for (Iterator<String> iter = requestParams.keySet().iterator(); iter.hasNext();) {
            String name = (String) iter.next();
            String[] values = (String[]) requestParams.get(name);
            String valueStr = "";
            for (int i = 0; i < values.length; i++) {
                valueStr = (i == values.length - 1) ? valueStr + values[i] : valueStr + values[i] + ",";
            }
            // 乱码解决，这段代码在出现乱码时使用
//            valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");
            params.put(name, valueStr);
        }

        System.out.println(params);//查看参数都有哪些
        //验证签名（支付宝公钥）
        boolean signVerified = AlipaySignature.rsaCheckV1(params, alipayPublicKey, charSet, signType); // 调用SDK验证签名
        //验证签名通过
        if(signVerified){
            // 商户订单号
            String out_trade_no = new String(request.getParameter("out_trade_no").getBytes("ISO-8859-1"), "UTF-8");

            // 支付宝交易流水号
            String trade_no = new String(request.getParameter("trade_no").getBytes("ISO-8859-1"), "UTF-8");

            // 付款金额
            float money = Float.parseFloat(new String(request.getParameter("total_amount").getBytes("ISO-8859-1"), "UTF-8"));

            System.out.println("商户订单号="+out_trade_no);
            System.out.println("支付宝交易号="+trade_no);
            System.out.println("付款金额="+money);

            /**
             * 这里可以对数据进行crud操作*/

            //跳转到提示页面（成功或者失败的提示页面）
            return "paySuccess";
        }else
            return "payError";
    }

}
