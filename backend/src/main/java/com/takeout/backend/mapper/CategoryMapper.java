package com.takeout.backend.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.takeout.backend.pojo.Category;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CategoryMapper extends BaseMapper<Category> {
}
