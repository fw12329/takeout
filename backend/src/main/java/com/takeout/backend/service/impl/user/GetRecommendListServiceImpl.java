package com.takeout.backend.service.impl.user;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.takeout.backend.mapper.SellerMapper;
import com.takeout.backend.pojo.Seller;
import com.takeout.backend.service.user.GetRecommendListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetRecommendListServiceImpl implements GetRecommendListService {
    @Autowired
    private SellerMapper sellerMapper;

    @Override
    public List<Seller> getRecommendList(Integer page) {
        IPage<Seller> sellerIPage = new Page<>(page,10);
        QueryWrapper<Seller> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("*, count(*) as total")
                .groupBy("seller_id")
                .orderByDesc("total");
        List<Seller> sellerList = sellerMapper.selectPage(sellerIPage,queryWrapper).getRecords();
        return sellerList;
    }
}
