package com.takeout.backend.service.impl.seller.order;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.takeout.backend.mapper.OrdersMapper;
import com.takeout.backend.mapper.SellerMapper;
import com.takeout.backend.pojo.Orders;
import com.takeout.backend.pojo.Seller;
import com.takeout.backend.pojo.User;
import com.takeout.backend.service.impl.utils.UserDetailsImpl;
import com.takeout.backend.service.seller.order.GetOrdersListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class GetOrdersListServiceImpl implements GetOrdersListService {
    @Autowired
    private OrdersMapper ordersMapper;

    @Autowired
    private SellerMapper sellerMapper;


    @Override
    public List<Orders> getOrders(Integer page) {
        UsernamePasswordAuthenticationToken authentication =
                (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();

        UserDetailsImpl loginUser = (UserDetailsImpl) authentication.getPrincipal();
        User user = loginUser.getUser();

        String open_id = user.getOpenId();

        QueryWrapper<Seller> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("open_id",open_id);

        Seller seller = sellerMapper.selectOne(queryWrapper);
        if(seller == null) {
            throw new RuntimeException("该用户没有创建商家");
        }

        QueryWrapper<Orders> queryWrapper1 = new QueryWrapper<>();
        queryWrapper1.eq("seller_id",seller.getSellerId());


        IPage<Orders> ordersIPage = new Page<>(page,10);
        List<Orders> orders = ordersMapper.selectPage(ordersIPage,queryWrapper1).getRecords();


        return orders;
    }
}
