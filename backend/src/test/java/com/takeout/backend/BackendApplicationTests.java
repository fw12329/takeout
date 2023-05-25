package com.takeout.backend;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.enums.SqlLike;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.takeout.backend.mapper.CommodityCategoryMapper;
import com.takeout.backend.mapper.OrdersMapper;
import com.takeout.backend.mapper.SellerMapper;
import com.takeout.backend.pojo.Commoditycategory;
import com.takeout.backend.pojo.Orders;
import com.takeout.backend.pojo.Seller;
import org.apache.ibatis.session.RowBounds;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.spec.AlgorithmParameterSpec;
import java.util.*;
import java.util.stream.Collectors;


@SpringBootTest
class BackendApplicationTests {

    @Autowired
    private SellerMapper sellerMapper;

    @Autowired
    private CommodityCategoryMapper commodityCategoryMapper;

    @Autowired
    private OrdersMapper ordersMapper;

    @Test
    void contextLoads() throws Exception {
        QueryWrapper<Seller> sellerQueryWrapper = new QueryWrapper<>();
        String keyword = "烧烤";
        sellerQueryWrapper.like("seller_name", keyword).or()
                .inSql("seller_id", "SELECT seller_id FROM commoditycategory WHERE name = '\" + keyword + \"'");
        List<Seller> sellers = sellerMapper.selectList(sellerQueryWrapper);
        System.out.println(sellers);
        List<Integer> sellerIds = sellers.stream()
                .map(Seller::getSellerId)
                .collect(Collectors.toList());
        QueryWrapper<Orders> ordersQueryWrapper = new QueryWrapper<>();
        ordersQueryWrapper.select("seller_id", "COUNT(*) as order_count");
        ordersQueryWrapper.in("seller_id", sellerIds);
        ordersQueryWrapper.groupBy("seller_id");
        ordersQueryWrapper.orderByDesc("order_count");


        int offset = (1 - 1) * 10;
        RowBounds rowBounds = new RowBounds(offset, 10);

        List<Seller> sellersList = ordersMapper.getSellersWithOrders(rowBounds);
        long total = sellersList.size();

        IPage<Seller> pages = new Page<>(1, 10,total);
        pages.setRecords(sellersList);
        System.out.println(pages.setRecords(sellersList));




        System.out.println(sellersList);








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
