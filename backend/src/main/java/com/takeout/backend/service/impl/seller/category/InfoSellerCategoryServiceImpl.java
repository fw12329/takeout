package com.takeout.backend.service.impl.seller.category;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.takeout.backend.mapper.SellerCategoryMapper;
import com.takeout.backend.mapper.SellerMapper;
import com.takeout.backend.pojo.Seller;
import com.takeout.backend.pojo.Sellercategory;
import com.takeout.backend.pojo.User;
import com.takeout.backend.service.impl.utils.UserDetailsImpl;
import com.takeout.backend.service.seller.category.InfoSellerCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InfoSellerCategoryServiceImpl implements InfoSellerCategoryService {

    @Autowired
    private SellerCategoryMapper sellerCategoryMapper;

    @Autowired
    private SellerMapper sellerMapper;


    @Override
    public List<Sellercategory> infoSellerCategory() {
        UsernamePasswordAuthenticationToken authentication =
                (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();

        UserDetailsImpl loginUser = (UserDetailsImpl) authentication.getPrincipal();
        User user = loginUser.getUser();

        QueryWrapper<Seller> queryWrapper = new QueryWrapper();
        queryWrapper.eq("open_id",user.getOpenId());
        Seller seller = sellerMapper.selectOne(queryWrapper);

        QueryWrapper<Sellercategory> queryWrapper1 = new QueryWrapper();
        queryWrapper.eq("seller_id",seller.getSellerId());
        return sellerCategoryMapper.selectList(queryWrapper1);
    }
}
