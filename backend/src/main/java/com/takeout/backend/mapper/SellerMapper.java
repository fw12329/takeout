package com.takeout.backend.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.takeout.backend.pojo.Seller;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SellerMapper extends BaseMapper<Seller> {
}
