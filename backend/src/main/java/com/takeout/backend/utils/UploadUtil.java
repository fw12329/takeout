package com.takeout.backend.utils;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class UploadUtil {
    private static final long MAX_IMAGE_SIZE = 1000000000000000L;
    private static final String ALI_DOMAIN = "https://takeoutimage.oss-cn-beijing.aliyuncs.com/";

    public static Map<String,String> upload(MultipartFile imageFile) {
        Map<String,String> map = new HashMap<>();
        if(imageFile.isEmpty()) {
            map.put("error_message","图片为空上传失败");
            return map;
        }
        if(imageFile.getSize() > MAX_IMAGE_SIZE){
            map.put("error_message","图片大小超出限制上传失败");
            return map;
        }
        String originalFilename = imageFile.getOriginalFilename();
        String ext = "." + originalFilename.split("\\.")[1];
        String uuid = UUID.randomUUID().toString().replace("-","");
        String fileName = uuid + ext;
        String endpoint = "http://oss-cn-beijing.aliyuncs.com";
        String accessKeyId = "LTAI5tRdLXVvb5N15Zer7Q9J";
        String accessKeySecret="AaSvgSTSyQtZbKVNKQs0oXcyRJ4B75";
        OSS ossClient = new OSSClientBuilder().build(endpoint,accessKeyId,accessKeySecret);
        try {
            ossClient.putObject("takeoutimage",fileName,imageFile.getInputStream());
            ossClient.shutdown();
            map.put("error_message","success");
            map.put("photo",ALI_DOMAIN + fileName);
            return map;
        } catch (IOException e) {
            map.put("error_message",e.getMessage());
            return map;
        }
    }
}
