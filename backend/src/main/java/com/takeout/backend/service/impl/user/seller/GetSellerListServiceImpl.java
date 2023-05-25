package com.takeout.backend.service.impl.user.seller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.takeout.backend.mapper.SellerMapper;
import com.takeout.backend.pojo.Seller;
import com.takeout.backend.service.user.seller.GetSellerListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetSellerListServiceImpl implements GetSellerListService {

    @Autowired
    private SellerMapper sellerMapper;
    @Override
    public List<Seller> getSellerlist(Integer page) {
        IPage<Seller> sellerIPage = new Page<>(page,10);


        return sellerMapper.selectPage(sellerIPage,null).getRecords();
    }
}
