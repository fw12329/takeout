package com.takeout.backend.service.impl.user.order;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.takeout.backend.mapper.CommodityMapper;
import com.takeout.backend.mapper.DetailsMapper;
import com.takeout.backend.pojo.Commodity;
import com.takeout.backend.pojo.Details;
import com.takeout.backend.pojo.User;
import com.takeout.backend.service.impl.utils.UserDetailsImpl;
import com.takeout.backend.service.user.order.AddService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class AddServiceImpl implements AddService {

    @Autowired
    private DetailsMapper detailsMapper;

    @Autowired
    private CommodityMapper commodityMapper;

    @Override
    public Map<String, String> add(Map<String, String> data) {
        UsernamePasswordAuthenticationToken authenticationToken = (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl loginUser = (UserDetailsImpl) authenticationToken.getPrincipal();
        User user = loginUser.getUser();
        Map<String,String> map = new HashMap<>();
        Integer product_id = Integer.parseInt(data.get("product_id"));
        Integer seller_id = Integer.parseInt(data.get("seller_id"));
        QueryWrapper<Commodity> queryWrapper1 = new QueryWrapper<>();
        queryWrapper1.eq("product_id",product_id).eq("seller_id",seller_id);
        Commodity commodity = commodityMapper.selectOne(queryWrapper1);
        UpdateWrapper<Details> updateWrapper = new UpdateWrapper<>();
        UpdateWrapper<Commodity> updateWrapper1 = new UpdateWrapper<>();
        if(commodity.getStock() > 0) {
            QueryWrapper<Details> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("user_id",user.getUserId()).eq("product_id",product_id).eq("seller_id",seller_id);
            Details details = detailsMapper.selectOne(queryWrapper);
            if(details != null) {
                Integer stock = details.getQuantity() + 1;
                Double price = stock * commodity.getPrice();
                updateWrapper.eq("user_id",user.getUserId()).eq("product_id",product_id).eq("seller_id",seller_id).set("quantity",stock).set("price",price);
                detailsMapper.update(null,updateWrapper);
            } else {
                Details details1 = new Details(null,null,product_id,1,commodity.getPrice(),new Date(),user.getUserId(),seller_id);
                detailsMapper.insert(details1);

            }
            Integer stock = commodity.getStock() - 1;
            updateWrapper1.eq("product_id",product_id).eq("seller_id",seller_id).set("stock",stock);
            commodityMapper.update(null,updateWrapper1);
            map.put("error_message","success");
        } else {
            map.put("error_message","商品库存不足");
        }


        return map;
    }
}
