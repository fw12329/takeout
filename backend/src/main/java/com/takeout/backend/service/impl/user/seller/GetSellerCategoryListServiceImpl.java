package com.takeout.backend.service.impl.user.seller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.takeout.backend.mapper.CommodityMapper;
import com.takeout.backend.mapper.SellerCategoryMapper;
import com.takeout.backend.mapper.SpecMapper;
import com.takeout.backend.mapper.SpecsMapper;
import com.takeout.backend.pojo.*;
import com.takeout.backend.service.impl.utils.UserDetailsImpl;
import com.takeout.backend.service.user.seller.GetSellerCategoryListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class GetSellerCategoryListServiceImpl implements GetSellerCategoryListService {

    @Autowired
    private SellerCategoryMapper sellerCategoryMapper;
    @Autowired
    private CommodityMapper commodityMapper;

    @Autowired
    private SpecsMapper specsMapper;

    @Autowired
    private SpecMapper specMapper;

    @Override
    public List<Map<String,Object>> getSellerCategoryList(Integer seller_id) {
        UsernamePasswordAuthenticationToken authenticationToken = (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl loginUser = (UserDetailsImpl) authenticationToken.getPrincipal();
        User user = loginUser.getUser();

        if(user == null) {
            throw new RuntimeException("未登录");
        }


        QueryWrapper<Sellercategory> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("seller_id",seller_id);
        List<Sellercategory> seller_category_list = sellerCategoryMapper.selectList(queryWrapper);
        List<Map<String,Object>> list1 = new ArrayList<>();

        for(int i = 0;i < seller_category_list.size();i++) {
            Sellercategory sellercategory = seller_category_list.get(i);
            Integer seller_category_id = sellercategory.getId();
            String seller_category_name = sellercategory.getName();
            Map<String,Object> map = new HashMap<>();
            map.put("seller_category_id",seller_category_id);
            map.put("seller_category_name",seller_category_name);
            QueryWrapper<Commodity> queryWrapper1 = new QueryWrapper<>();
            queryWrapper1.eq("sellercategory_id",seller_category_id);
            List<Commodity> commodityList = commodityMapper.selectList(queryWrapper1);
            List<Map<String,Object>> list = new ArrayList<>();
            for(int j = 0;j < commodityList.size();j++) {
                Commodity commodity = commodityList.get(j);
                Map<String,Object> map1 = new HashMap<>();
                map1.put("commodity_seller",commodity.getSellerId());
                map1.put("commodity_photo",commodity.getImage());
                map1.put("commodity_name",commodity.getProductName());
                map1.put("commodity_description",commodity.getDescription());
                QueryWrapper<Specs> queryWrapper2 = new QueryWrapper<>();
                queryWrapper2.eq("product_id",commodity.getProductId());
                List<Specs> specs_list = specsMapper.selectList(queryWrapper2);
                List<Map<String,Object>> list2 = new ArrayList<>();
                for(int k = 0;k < specs_list.size();k++) {
                    Map<String,Object> map2 = new HashMap<>();
                    Specs specs = specs_list.get(k);
                    map2.put("specs_name",specs.getName());
                    QueryWrapper<Spec> queryWrapper3 = new QueryWrapper<>();
                    queryWrapper3.eq("specs_id",specs.getId());
                    List<Spec> specList = specMapper.selectList(null);
                    map2.put("spec_list",specList);
                    list2.add(map2);
                }
                map1.put("commodity_spec_list",list2);
                list.add(map1);
            }
            map.put("seller_product_list",list);
            list1.add(map);
        }

        return list1;
    }
}
