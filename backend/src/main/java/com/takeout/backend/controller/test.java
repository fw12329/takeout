package com.takeout.backend.controller;

import com.alibaba.fastjson.TypeReference;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.takeout.backend.mapper.*;
import com.takeout.backend.pojo.*;
import com.takeout.backend.service.impl.utils.UserDetailsImpl;
import com.takeout.backend.utils.HttpClientUtil;
import com.takeout.backend.utils.UploadUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import javax.swing.text.View;
import java.lang.reflect.Array;
import java.security.spec.AlgorithmParameterSpec;
import java.util.*;
import java.util.stream.Collectors;

@RestController
public class test {

    @Autowired
    private OrdersMapper orderMapper;

    @Autowired
    private SellerMapper sellerMapper;


    @Autowired
    private DetailsMapper detailsMapper;

    @Autowired
    private CommodityMapper commodityMapper;



    @GetMapping("/test/")
    public String test() throws Exception {
            return "abc";


//        List<Map<String,Object>> list = new ArrayList<>();
//        List<Map<String,String>> commodity = new ArrayList<>();
//        Map<String,Object> map = new HashMap<>();
//        map.put("order_id",1);
//        map.put("order_price",2000.00);
//        map.put("order_status",0);
//        map.put("commodity",commodity);
//        list.add(map);
//        objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
//        ObjectWriter objectWriter = objectMapper.writerWithView(Views.Public.class);
//        String json = objectWriter.writeValueAsString(list);
    }

    private String decrypt(String encryptedData, String iv, String sessionKey) throws Exception {
        byte[] encryptedDataBytes = Base64.getDecoder().decode(encryptedData);
        byte[] sessionKeyBytes = Base64.getDecoder().decode(sessionKey);
        byte[] ivBytes = Base64.getDecoder().decode(iv);

        SecretKeySpec skeySpec = new SecretKeySpec(sessionKeyBytes, "AES");
        AlgorithmParameterSpec ivSpec = new IvParameterSpec(ivBytes);

        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        cipher.init(Cipher.DECRYPT_MODE, skeySpec, ivSpec);

        byte[] decryptedDataBytes = cipher.doFinal(encryptedDataBytes);
        return new String(decryptedDataBytes);
    }

}
class Views {
    public static class Public {}
    public static class Internal extends Public {}
}






