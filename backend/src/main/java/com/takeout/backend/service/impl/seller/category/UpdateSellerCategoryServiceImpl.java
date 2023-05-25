package com.takeout.backend.service.impl.seller.category;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.takeout.backend.mapper.SellerCategoryMapper;
import com.takeout.backend.mapper.SellerMapper;
import com.takeout.backend.pojo.Seller;
import com.takeout.backend.pojo.Sellercategory;
import com.takeout.backend.pojo.User;
import com.takeout.backend.service.impl.utils.UserDetailsImpl;
import com.takeout.backend.service.seller.category.UpdateSellerCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;


@Service
public class UpdateSellerCategoryServiceImpl implements UpdateSellerCategoryService {
    @Autowired
    private SellerMapper sellerMapper;

    @Autowired
    private SellerCategoryMapper sellerCategoryMapper;

    @Override
    public Map<String, String> updateSellerCategory(Integer id, String name) {
        UsernamePasswordAuthenticationToken authentication =
                (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();

        UserDetailsImpl loginUser = (UserDetailsImpl) authentication.getPrincipal();
        User user = loginUser.getUser();

        Map<String,String> map = new HashMap<>();
        QueryWrapper<Seller> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("open_id",user.getOpenId());
        Seller seller = sellerMapper.selectOne(queryWrapper);
        if(seller == null) {
            map.put("error_message","该商家不存在");
            return map;
        }
        QueryWrapper<Sellercategory> queryWrapper1 = new QueryWrapper<>();
        queryWrapper1.eq("seller_id",seller.getSellerId()).eq("name",name);
        Sellercategory sellercategory = sellerCategoryMapper.selectOne(queryWrapper1);
        if(sellercategory == null) {
            map.put("error_message","该商家不存在该分类");
            return map;
        }
        Sellercategory new_sellercategory = new Sellercategory(
                sellercategory.getId(),
                sellercategory.getSellerId(),
                name
        );
        sellerCategoryMapper.updateById(new_sellercategory);
        map.put("error_message","success");
        return map;
    }
}
