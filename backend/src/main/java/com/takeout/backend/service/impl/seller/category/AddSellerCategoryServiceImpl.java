package com.takeout.backend.service.impl.seller.category;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.takeout.backend.mapper.SellerCategoryMapper;
import com.takeout.backend.mapper.SellerMapper;
import com.takeout.backend.pojo.Seller;
import com.takeout.backend.pojo.Sellercategory;
import com.takeout.backend.pojo.User;
import com.takeout.backend.service.impl.utils.UserDetailsImpl;
import com.takeout.backend.service.seller.category.AddSellerCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class AddSellerCategoryServiceImpl implements AddSellerCategoryService {
    @Autowired
    private SellerCategoryMapper sellerCategoryMapper;

    @Autowired
    private SellerMapper sellerMapper;


    @Override
    public Map<String, String> addsellerCategory(String name) {
        UsernamePasswordAuthenticationToken authentication =
                (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();

        UserDetailsImpl loginUser = (UserDetailsImpl) authentication.getPrincipal();
        User user = loginUser.getUser();

        Map<String,String> map = new HashMap<>();
        QueryWrapper<Seller> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("open_id",user.getOpenId());
        Seller seller = sellerMapper.selectOne(queryWrapper);
        Sellercategory sellerCategory = new Sellercategory(null,seller.getSellerId(),name);
        sellerCategoryMapper.insert(sellerCategory);
        map.put("error_message","success");
        return map;
    }
}
