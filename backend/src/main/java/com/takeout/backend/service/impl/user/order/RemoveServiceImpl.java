package com.takeout.backend.service.impl.user.order;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.takeout.backend.mapper.CommodityMapper;
import com.takeout.backend.mapper.DetailsMapper;
import com.takeout.backend.pojo.Commodity;
import com.takeout.backend.pojo.Details;
import com.takeout.backend.pojo.User;
import com.takeout.backend.service.impl.utils.UserDetailsImpl;
import com.takeout.backend.service.user.order.RemoveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class RemoveServiceImpl implements RemoveService {
    @Autowired
    private CommodityMapper commodityMapper;

    @Autowired
    private DetailsMapper detailsMapper;

    @Override
    public Map<String, String> remove(Map<String, String> data) {
        UsernamePasswordAuthenticationToken authenticationToken = (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl loginUser = (UserDetailsImpl) authenticationToken.getPrincipal();
        User user = loginUser.getUser();
        Map<String,String> map = new HashMap<>();
        Integer product_id = Integer.parseInt(data.get("product_id"));
        Integer seller_id = Integer.parseInt(data.get("seller_id"));
        QueryWrapper<Details> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id",user.getUserId()).eq("product_id",product_id).eq("seller_id",seller_id);
        Details details = detailsMapper.selectOne(queryWrapper);

        QueryWrapper<Commodity> queryWrapper1 = new QueryWrapper<>();
        queryWrapper1.eq("product_id",product_id).eq("seller_id",seller_id);
        Commodity commodity = commodityMapper.selectOne(queryWrapper1);

        if(detailsMapper.selectOne(queryWrapper) != null) {
            UpdateWrapper<Details> updateWrapper1 = new UpdateWrapper<>();
            Integer quantity = details.getQuantity() - 1;
            updateWrapper1.eq("user_id",user.getUserId()).eq("product_id",product_id).eq("seller_id",seller_id).set("quantity",quantity).set("price",quantity * commodity.getPrice());
            detailsMapper.update(null,updateWrapper1);
            if(quantity < 1) {
                QueryWrapper<Details> queryWrapper2 = new QueryWrapper<>();
                queryWrapper2.eq("user_id",user.getUserId()).eq("product_id",product_id).eq("seller_id",seller_id);
                detailsMapper.delete(queryWrapper2);
            }
            UpdateWrapper<Commodity> updateWrapper = new UpdateWrapper<>();
            Integer stock = commodity.getStock() + 1;
            updateWrapper.eq("product_id",product_id).eq("seller_id",seller_id).set("stock",stock);
            commodityMapper.update(null,updateWrapper);

            map.put("error_message","success");

        } else {
            map.put("error_message","该商品订单不存在");
            return map;
        }



        
        return map;
    }
}
