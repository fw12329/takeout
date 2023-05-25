package com.takeout.backend.service.impl.user.order;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.takeout.backend.mapper.CommodityMapper;
import com.takeout.backend.mapper.DetailsMapper;
import com.takeout.backend.mapper.OrdersMapper;
import com.takeout.backend.mapper.SellerMapper;
import com.takeout.backend.pojo.*;
import com.takeout.backend.service.impl.utils.UserDetailsImpl;
import com.takeout.backend.service.user.order.GetListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class GetListServiceImpl implements GetListService {
    @Autowired
    private OrdersMapper orderMapper;

    @Autowired
    private SellerMapper sellerMapper;


    @Autowired
    private DetailsMapper detailsMapper;

    @Autowired
    private CommodityMapper commodityMapper;
    @Override
    @Transactional
    public List<Map<String,Object>> getList(Integer page) {
        UsernamePasswordAuthenticationToken authenticationToken = (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl loginUser = (UserDetailsImpl) authenticationToken.getPrincipal();
        User user = loginUser.getUser();
        QueryWrapper<Orders> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("open_id",user.getOpenId());


        List<Map<String,Object>> orders = new ArrayList<>();
        IPage<Orders> ordersIPage = new Page<>(page,10);
        List<Orders> list = orderMapper.selectPage(ordersIPage,queryWrapper).getRecords();
        for(int i = 0;i < list.size();i++) {
            Map<String,Object> order = new HashMap<>();
            Integer order_id = list.get(i).getOrderId();
            Double order_price = list.get(i).getTotalPrice();
            QueryWrapper<Seller> queryWrapper1 = new QueryWrapper<>();
            queryWrapper1.eq("seller_id",list.get(i).getSellerId());
            Seller seller = sellerMapper.selectOne(queryWrapper1);
            String seller_name = seller.getSellerName();

            order.put("order_id",order_id);
            order.put("order_price",order_price);
            order.put("seller_name",seller_name);


            QueryWrapper<Details> queryWrapper2 = new QueryWrapper<>();

            List<Details> details = detailsMapper.selectList(queryWrapper2);
            ArrayList<Map<String,String>> arrayList = new ArrayList<>();
            for(int j = 0;j < details.size();j++) {
                Map<String,String> map = new HashMap<>();
                Integer product_id = details.get(j).getProductId();
                QueryWrapper<Commodity> queryWrapper3 = new QueryWrapper<>();
                queryWrapper3.eq("product_id",product_id);
                Commodity commodity = commodityMapper.selectOne(queryWrapper3);

                map.put("commodity_name",commodity.getProductName());
                map.put("commodity_photo",commodity.getImage());
                arrayList.add(map);
            }
            order.put("commodity_list",arrayList);
            orders.add(order);
        }



        return orders;
    }
}
