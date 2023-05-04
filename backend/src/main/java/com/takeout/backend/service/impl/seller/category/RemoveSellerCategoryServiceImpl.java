package com.takeout.backend.service.impl.seller.category;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.takeout.backend.mapper.SellerCategoryMapper;
import com.takeout.backend.mapper.SellerMapper;
import com.takeout.backend.pojo.Seller;
import com.takeout.backend.pojo.Sellercategory;
import com.takeout.backend.pojo.User;
import com.takeout.backend.service.impl.utils.UserDetailsImpl;
import com.takeout.backend.service.seller.category.RemoveSellerCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class RemoveSellerCategoryServiceImpl implements RemoveSellerCategoryService {

    @Autowired
    private SellerCategoryMapper sellerCategoryMapper;

    @Autowired
    private SellerMapper sellerMapper;

    @Override
    public Map<String, String> removeSellerCategory(Integer id) {
        UsernamePasswordAuthenticationToken authentication =
                (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();

        UserDetailsImpl loginUser = (UserDetailsImpl) authentication.getPrincipal();
        User user = loginUser.getUser();

        Map<String,String> map = new HashMap<>();

        QueryWrapper<Seller> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("open_id",user.getOpenId());
        Seller seller = sellerMapper.selectOne(queryWrapper);

        QueryWrapper<Sellercategory> queryWrapper1 = new QueryWrapper<>();
        queryWrapper.eq("seller_id",seller.getSellerId()).eq("id",id);
        if(sellerCategoryMapper.selectOne(queryWrapper1) == null) {
            map.put("error_message","您当前的店铺不存在这个类别");
        }
        sellerCategoryMapper.delete(queryWrapper1);
        map.put("error_message","success");
        return map;
    }
}
