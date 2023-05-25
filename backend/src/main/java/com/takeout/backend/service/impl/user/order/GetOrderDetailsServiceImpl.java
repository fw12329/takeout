package com.takeout.backend.service.impl.user.order;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.takeout.backend.mapper.*;
import com.takeout.backend.pojo.*;
import com.takeout.backend.service.impl.utils.UserDetailsImpl;
import com.takeout.backend.service.user.order.GetOrderDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
public class GetOrderDetailsServiceImpl implements GetOrderDetailsService {
    @Autowired
    private DetailsMapper detailsMapper;

    @Autowired
    private OrdersMapper ordersMapper;

    @Autowired
    private DetailsspecMapper detailsspecMapper;

    @Autowired
    private SpecMapper specMapper;

    @Autowired
    private CommodityMapper commodityMapper;


    @Override
    public ArrayList<Map<String,Object>> getorderdetails(Map<String, String> data) {
        UsernamePasswordAuthenticationToken authenticationToken = (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl loginUser = (UserDetailsImpl) authenticationToken.getPrincipal();
        User user = loginUser.getUser();
        String order_id = data.get("order_id");

        QueryWrapper<Orders> queryWrapper_orders = new QueryWrapper<>();
        queryWrapper_orders.eq("order_id",order_id);

        Orders orders = ordersMapper.selectOne(queryWrapper_orders);

        QueryWrapper<Details> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("order_id",order_id);
        List<Details> details = detailsMapper.selectList(queryWrapper);

        ArrayList<Map<String,Object>> order_details = new ArrayList<>();
        for(int i = 0;i < details.size();i++) {
            Map<String,Object> map = new HashMap<>();
            Integer product_id = details.get(i).getProductId();
            QueryWrapper<Commodity> queryWrapper_commodity = new QueryWrapper<>();
            queryWrapper_commodity.eq("product_id",product_id);

            Commodity commodity = commodityMapper.selectOne(queryWrapper_commodity);
            String product_name = commodity.getProductName();

            Integer details_id = details.get(i).getId();
            QueryWrapper<Detailsspec> queryWrapper1 = new QueryWrapper<>();
            queryWrapper1.eq("details_id",details_id);
            List<Detailsspec> detailsspec = detailsspecMapper.selectList(queryWrapper1);
            List<String> spec_list = new ArrayList<>();
            for(int j = 0;j < detailsspec.size();j++) {
                Integer spec_id = detailsspec.get(j).getSpecId();
                Integer specs_id = detailsspec.get(j).getSpecsId();
                QueryWrapper<Spec> queryWrapper2 = new QueryWrapper<>();
                queryWrapper2.eq("id",spec_id).eq("specs_id",specs_id);
                Spec spec = specMapper.selectOne(queryWrapper2);
                spec_list.add(spec.getSpecName());
            }
            map.put("commodity_spec_list",spec_list);
            map.put("commodity_quantity",details.get(i).getQuantity());
            map.put("commodity_name",product_name);
            map.put("orders_price",orders.getTotalPrice());

            order_details.add(map);

        }




        return order_details;
    }
}
