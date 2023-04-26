package com.takeout.backend.service.impl.user.order;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.takeout.backend.mapper.CommodityMapper;
import com.takeout.backend.mapper.DetailsMapper;
import com.takeout.backend.mapper.OrderMapper;
import com.takeout.backend.pojo.Commodity;
import com.takeout.backend.pojo.Details;
import com.takeout.backend.pojo.Order;
import com.takeout.backend.pojo.User;
import com.takeout.backend.service.impl.utils.UserDetailsImpl;
import com.takeout.backend.service.user.order.CommitOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
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
    private OrderMapper orderMapper;

    @Override
    public Map<String, String> commitOrder(Integer seller_id,List<Map<String, Integer>> mapList) {
        Map<String,String> map = new HashMap<>();

        UsernamePasswordAuthenticationToken authenticationToken = (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        System.out.println(authenticationToken);
        UserDetailsImpl loginUser = (UserDetailsImpl) authenticationToken.getPrincipal();

        User user = loginUser.getUser();
        Order order = new Order(null,user.getUserId(),seller_id,new Date(),"0",new Date(),new Date());
        orderMapper.insert(order);
        System.out.println(mapList.get(0));
        for(int i = 0;i < mapList.size();i++) {

            Map<String,Integer> orderDetails = mapList.get(i);
            Integer product_id = Integer.parseInt(orderDetails.get("product_id").toString());
            QueryWrapper<Commodity> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("product_id",product_id);
            Commodity commodity = commodityMapper.selectOne(queryWrapper);
            Integer number = Integer.parseInt(orderDetails.get("number").toString());
            Double price = commodity.getPrice() * number;
            Details details = new Details(null,1,product_id,number,price,new Date());
            detailsMapper.insert(details);
        }
        map.put("error_message","success");
        return map;
    }
}
