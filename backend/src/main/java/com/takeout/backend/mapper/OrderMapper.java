package com.takeout.backend.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.takeout.backend.pojo.Order;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OrderMapper extends BaseMapper<Order> {
}
