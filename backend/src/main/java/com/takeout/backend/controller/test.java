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
    public List<Map<String,Object>> test() throws Exception {
        QueryWrapper<Orders> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("open_id","RkJ+Yanb6sGYZuvPQnK3Vw==");


        List<Map<String,Object>> orders = new ArrayList<>();
        List<Orders> list = orderMapper.selectList(queryWrapper);
        for(int i = 0;i < list.size();i++) {
            Map<String,Object> order = new HashMap<>();
            Integer order_id = list.get(i).getOrderId();
            Double order_price = list.get(i).getTotalPrice();
            QueryWrapper<Seller> queryWrapper1 = new QueryWrapper<>();
            queryWrapper1.eq("seller_id",list.get(i).getSellerId());
            Seller seller = sellerMapper.selectOne(queryWrapper1);
            String seller_name = seller.getSellerName();

            order.put("order_id",order_id);
            order.put("order_price",order_price);
            order.put("seller_name",seller_name);


            QueryWrapper<Details> queryWrapper2 = new QueryWrapper<>();

            List<Details> details = detailsMapper.selectList(queryWrapper2);
            ArrayList<Map<String,String>> arrayList = new ArrayList<>();
            for(int j = 0;j < details.size();j++) {
                Map<String,String> map = new HashMap<>();
                Integer product_id = details.get(j).getProductId();
                QueryWrapper<Commodity> queryWrapper3 = new QueryWrapper<>();
                queryWrapper3.eq("product_id",product_id);
                System.out.println(product_id);
                Commodity commodity = commodityMapper.selectOne(queryWrapper3);

                map.put("commodity_name",commodity.getProductName());
                map.put("commodity_photo",commodity.getImage());
                arrayList.add(map);
            }
            order.put("commodity_list",arrayList);
            orders.add(order);
        }



        return orders;
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






