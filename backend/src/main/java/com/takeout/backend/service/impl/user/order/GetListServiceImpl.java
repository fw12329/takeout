package com.takeout.backend.service.impl.user.order;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.takeout.backend.mapper.OrderMapper;
import com.takeout.backend.pojo.Order;
import com.takeout.backend.pojo.User;
import com.takeout.backend.service.impl.utils.UserDetailsImpl;
import com.takeout.backend.service.user.order.GetListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetListServiceImpl implements GetListService {
    @Autowired
    private OrderMapper orderMapper;
    @Override
    public List<Order> getList() {
        UsernamePasswordAuthenticationToken authenticationToken = (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl loginUser = (UserDetailsImpl) authenticationToken.getPrincipal();
        User user = loginUser.getUser();
        QueryWrapper<Order> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id",user.getUser_id());
        return orderMapper.selectList(queryWrapper);
    }
}
