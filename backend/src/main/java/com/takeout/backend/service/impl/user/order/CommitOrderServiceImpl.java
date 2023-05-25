package com.takeout.backend.service.impl.user.order;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.takeout.backend.mapper.*;
import com.takeout.backend.pojo.*;
import com.takeout.backend.service.impl.utils.UserDetailsImpl;
import com.takeout.backend.service.user.order.CommitOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class CommitOrderServiceImpl implements CommitOrderService {

    @Autowired
    private DetailsMapper detailsMapper;
    @Autowired
    private OrdersMapper ordersMapper;


    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private SpecMapper specMapper;

    @Autowired
    private DetailsspecMapper detailsspecMapper;

    @Override
    @Transactional
    public Map<String, String> commitOrder(Integer seller_id,
                                           String contact_name,
                                           String contact_phone,
                                           String contact_address,
                                           String is_play,
                                           Integer status,
                                           String orders) throws JsonProcessingException {
        Map<String,String> map = new HashMap<>();
        UsernamePasswordAuthenticationToken authenticationToken = (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl loginUser = (UserDetailsImpl) authenticationToken.getPrincipal();

        User user = loginUser.getUser();
        String open_id = user.getOpenId();

        Orders or = new Orders(null,null,new Date(),new Date(),contact_name,contact_phone,contact_address,status,is_play,seller_id,open_id);
        ordersMapper.insert(or);
        Double orders_Price = 0.00;
        ArrayList<Object> order = objectMapper.readValue(orders,ArrayList.class);
        for(int i = 0;i < order.size();i++) {
            Map<String,Object> details = (Map<String,Object>)order.get(i);
            Integer product_id = Integer.parseInt(details.get("product").toString());
            Integer quantity = Integer.parseInt(details.get("quantity").toString());
            ArrayList<Map<Object,Object>> spec = (ArrayList<Map<Object,Object>>)details.get("spec");
            Details details1 = new Details(null,or.getOrderId(),product_id,null,4);
            detailsMapper.insert(details1);
            Double spec_price = 0.00;
            for(int j = 0;j < spec.size();j++) {
                Map<Object,Object> specs = spec.get(j);
                Integer spec_id = Integer.parseInt(specs.get("spec").toString());
                Integer specs_id = Integer.parseInt(specs.get("specs").toString());
                Detailsspec detailsspec = new Detailsspec(null,spec_id,specs_id,details1.getId());
                detailsspecMapper.insert(detailsspec);
                QueryWrapper<Spec> queryWrapper = new QueryWrapper<>();
                queryWrapper.eq("id",spec_id).eq("specs_id",specs_id);
                Spec spec1 = specMapper.selectOne(queryWrapper);
                spec_price += spec1.getSpecPrice();
            }
            details1.setSpecPrice(spec_price * quantity);
            detailsMapper.updateById(details1);
            orders_Price += details1.getSpecPrice();


        }
        or.setTotalPrice(orders_Price);
        ordersMapper.updateById(or);
        map.put("error_message","success");
        map.put("order_id",or.getOrderId().toString());
        return map;


    }
}
