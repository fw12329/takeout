package com.takeout.backend.service.impl.seller.commodity;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.takeout.backend.mapper.*;
import com.takeout.backend.pojo.*;
import com.takeout.backend.service.impl.utils.UserDetailsImpl;
import com.takeout.backend.service.seller.commodity.InfoCommodityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class InfoCommodityServiceImpl implements InfoCommodityService {
    @Autowired
    private SellerMapper sellerMapper;

    @Autowired
    private CommodityMapper commodityMapper;

    @Autowired
    private SellerCategoryMapper sellerCategoryMapper;

    @Autowired
    private SpecsMapper specsMapper;

    @Autowired
    private SpecMapper specMapper;


    @Override
    public Map<String, Object> infoCommodity(Map<String, String> data) {
        Map<String,Object> map = new HashMap<>();
        UsernamePasswordAuthenticationToken authentication =
                (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();

        UserDetailsImpl loginUser = (UserDetailsImpl) authentication.getPrincipal();
        User user = loginUser.getUser();

        Integer commodity_id = Integer.parseInt(data.get("commodity_id"));


        QueryWrapper<Seller> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("open_id",user.getOpenId());
        Seller seller = sellerMapper.selectOne(queryWrapper);
        if(seller == null) {
            map.put("error_message","您没有创建商家");
            return map;
        }

        QueryWrapper<Commodity> queryWrapper1 = new QueryWrapper<>();
        queryWrapper1.eq("seller_id",seller.getSellerId()).eq("product_id",commodity_id);
        Commodity commodity = commodityMapper.selectOne(queryWrapper1);
        if(commodity == null) {
            map.put("error_message","您权限查看该商品");
            return map;
        }
        map.put("error_message","success");
        map.put("product_id",commodity.getProductId().toString());
        map.put("product_name",commodity.getProductName());
        map.put("image",commodity.getImage());
        map.put("description",commodity.getDescription());

        List<Map<String,Object>> specs_list = new ArrayList<>();
        QueryWrapper<Specs> specsQueryWrapper = new QueryWrapper<>();
        specsQueryWrapper.eq("product_id",commodity.getProductId());
        List<Specs> specs = specsMapper.selectList(specsQueryWrapper);
        for(int i = 0;i < specs.size();i++) {
            Map<String,Object> map1 = new HashMap<>();
            map1.put("specs_id",specs.get(i).getId());
            map1.put("specs_name",specs.get(i).getName());
            QueryWrapper<Spec> queryWrapper2 = new QueryWrapper<>();
            queryWrapper2.eq("specs_id",specs.get(i).getId());
            map1.put("spec_list",specMapper.selectList(queryWrapper2));
            specs_list.add(map1);
        }
        map.put("specs_list",specs_list);
        if(commodity.getSellercategoryId() == null) {
            return map;
        }
        map.put("seller_category_id",commodity.getSellercategoryId());
        QueryWrapper<Sellercategory> queryWrapper2 = new QueryWrapper<>();
        queryWrapper2.eq("seller_id",seller.getSellerId());
        Sellercategory sellercategory = sellerCategoryMapper.selectOne(queryWrapper2);
        map.put("seller_category_name",sellercategory.getName());
        return map;
    }
}
