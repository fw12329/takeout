package com.takeout.backend.service.impl.user.order;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.takeout.backend.mapper.DetailsMapper;
import com.takeout.backend.pojo.Details;
import com.takeout.backend.service.user.order.GetOrderDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
public class GetOrderDetailsServiceImpl implements GetOrderDetailsService {
    @Autowired
    private DetailsMapper detailsMapper;

    @Override
    public List<Details> getorderdetails(Map<String, String> data) {
        String order_id = data.get("order_id");

        QueryWrapper<Details> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("order_id",order_id);
        List<Details> list = detailsMapper.selectList(queryWrapper);

        return list;
    }
}
