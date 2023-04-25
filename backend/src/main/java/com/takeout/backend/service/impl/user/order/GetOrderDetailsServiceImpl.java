package com.takeout.backend.service.impl.user.order;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.takeout.backend.mapper.DetailsMapper;
import com.takeout.backend.pojo.Details;
import com.takeout.backend.pojo.User;
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

    @Override
    public List<Details> getorderdetails(Map<String, String> data) {
        UsernamePasswordAuthenticationToken authenticationToken = (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl loginUser = (UserDetailsImpl) authenticationToken.getPrincipal();
        User user = loginUser.getUser();
        String order_id = data.get("order_id");
        QueryWrapper<Details> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("order_id",order_id).eq("user_id",user.getUserId());
        List<Details> list = detailsMapper.selectList(queryWrapper);

        return list;
    }
}
