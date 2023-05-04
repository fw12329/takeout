package com.takeout.backend.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.takeout.backend.pojo.Spec;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SpecMapper extends BaseMapper<Spec> {
}
