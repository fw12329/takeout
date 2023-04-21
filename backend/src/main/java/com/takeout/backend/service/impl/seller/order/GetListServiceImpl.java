package com.takeout.backend.service.impl.seller.order;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.takeout.backend.mapper.OrderMapper;
import com.takeout.backend.pojo.Order;
import com.takeout.backend.service.seller.order.GetListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service
public class GetListServiceImpl implements GetListService {
    @Autowired
    private OrderMapper orderMapper;

    @Override
    public List<Order> getList(Map<String, String> data) {
        Integer seller_id = Integer.parseInt(data.get("seller_id"));
        QueryWrapper<Order> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("seller_id",seller_id);
        List<Order> list = orderMapper.selectList(queryWrapper);
        return list;
    }
}
