package com.takeout.backend.service.impl.seller.commodity;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.takeout.backend.mapper.CommodityMapper;
import com.takeout.backend.mapper.SellerMapper;
import com.takeout.backend.pojo.Commodity;
import com.takeout.backend.pojo.Seller;
import com.takeout.backend.pojo.User;
import com.takeout.backend.service.impl.utils.UserDetailsImpl;
import com.takeout.backend.service.seller.commodity.RemoveCommodityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class RemoveCommodityServiceImpl implements RemoveCommodityService {

    @Autowired
    private SellerMapper sellerMapper;

    @Autowired
    private CommodityMapper commodityMapper;

    @Override
    public Map<String, String> RemoveCommodity(Integer product_id) {
        UsernamePasswordAuthenticationToken authentication =
                (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();

        UserDetailsImpl loginUser = (UserDetailsImpl) authentication.getPrincipal();
        User user = loginUser.getUser();
        String open_id = user.getOpenId();

        Map<String,String> data = new HashMap<>();

        QueryWrapper<Seller> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("open_id",open_id);

        Seller seller = sellerMapper.selectOne(queryWrapper);
        if(seller == null) {
            data.put("error_message","该商家不存在");
            return data;
        }

        QueryWrapper<Commodity> queryWrapper1 = new QueryWrapper<>();
        queryWrapper1.eq("seller_id",seller.getSellerId()).eq("product_id",product_id);
        Commodity commodity = commodityMapper.selectOne(queryWrapper1);
        if(commodity == null) {
            data.put("error_message","该商家没有这个商品");
            return data;
        }

        Commodity new_Commodity = new Commodity(commodity.getProductId(),commodity.getProductName(),commodity.getImage(),commodity.getDescription(),null,commodity.getSellercategoryId(),null);
        commodityMapper.updateById(new_Commodity);
        data.put("error_message","success");
        return data;
    }
}
