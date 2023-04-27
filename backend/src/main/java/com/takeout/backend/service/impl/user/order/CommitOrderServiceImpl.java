package com.takeout.backend.service.impl.user.order;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.takeout.backend.mapper.CommodityMapper;
import com.takeout.backend.mapper.DetailsMapper;
import com.takeout.backend.mapper.OrdersMapper;
import com.takeout.backend.pojo.Commodity;
import com.takeout.backend.pojo.Details;
import com.takeout.backend.pojo.Orders;
import com.takeout.backend.pojo.User;
import com.takeout.backend.service.impl.utils.UserDetailsImpl;
import com.takeout.backend.service.user.order.CommitOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CommitOrderServiceImpl implements CommitOrderService {

    @Autowired
    private DetailsMapper detailsMapper;

    @Autowired
    private CommodityMapper commodityMapper;

    @Autowired
    private OrdersMapper ordersMapper;

    @Override
    public Map<String, String> commitOrder(Map<String,Object> data) {
        Map<String,String> map = new HashMap<>();
        UsernamePasswordAuthenticationToken authenticationToken = (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl loginUser = (UserDetailsImpl) authenticationToken.getPrincipal();

        User user = loginUser.getUser();
        Integer seller_id = Integer.parseInt(data.get("seller_id").toString());
        Orders orders = new Orders(null,user.getOpenId(),seller_id,"0",new Date(),new Date());
        ordersMapper.insert(orders);
        List<Map<String, Object>> list = (List<Map<String, Object>>) data.get("map");
        for(int i = 0;i < list.size();i++) {
            Map<String,Object> orderDetails = list.get(i);
            Integer product_id = (Integer)orderDetails.get("product_id");
            Integer number = (Integer)orderDetails.get("number");
            QueryWrapper<Commodity> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("product_id",product_id);
            Commodity commodity = commodityMapper.selectOne(queryWrapper);
            Double price = commodity.getPrice() * number;
            Details details = new Details(null,orders.getOrderId(),product_id,number,price,new Date());
            detailsMapper.insert(details);
        }
        map.put("error_message","success");
        return map;
//        try {
//
//        } catch (Exception e) {
//            System.out.println(e.getMessage());
//            map.put("error_message",e.getMessage());
//            return map;
//
//        }



    }
}
