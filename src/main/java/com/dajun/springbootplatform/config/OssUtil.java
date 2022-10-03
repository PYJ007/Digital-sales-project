package com.dajun.springbootplatform.config;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;

@Service
public class OssUtil {

    @Resource
    private OssProperties ossProperties;

    public String upload(MultipartFile file,String name) throws IOException {
        String endPoint=ossProperties.getEndpoint();
        String accessKeyId=ossProperties.getKeyid();
        String keySecret=ossProperties.getKeysecret();
        String bucketName=ossProperties.getBucketname();
        if (file==null||file.isEmpty()) return null;
        OSS ossClient = new OSSClientBuilder().build(endPoint, accessKeyId, keySecret);
        String url = "platform/image/"+name;
        ossClient.putObject(bucketName,url,file.getInputStream());
        ossClient.shutdown();
        return url;
    }
}
