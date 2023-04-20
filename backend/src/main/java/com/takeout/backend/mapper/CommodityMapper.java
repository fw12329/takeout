package com.takeout.backend.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.takeout.backend.pojo.Commodity;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CommodityMapper extends BaseMapper<Commodity> {
}
