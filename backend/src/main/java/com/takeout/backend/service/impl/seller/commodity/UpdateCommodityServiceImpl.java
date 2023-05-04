package com.takeout.backend.service.impl.seller.commodity;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.takeout.backend.mapper.CommodityMapper;
import com.takeout.backend.mapper.SellerMapper;
import com.takeout.backend.pojo.Commodity;
import com.takeout.backend.pojo.Seller;
import com.takeout.backend.pojo.User;
import com.takeout.backend.service.impl.utils.UserDetailsImpl;
import com.takeout.backend.service.seller.commodity.UpdateCommodityService;
import com.takeout.backend.utils.UploadUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;

@Service
public class UpdateCommodityServiceImpl implements UpdateCommodityService {

    @Autowired
    private SellerMapper sellerMapper;

    @Autowired
    private CommodityMapper commodityMapper;


    @Override
    @Transactional
    public Map<String, String> updateCommodity(Integer product_id,MultipartFile image,String product_name, Integer sellercategory_id, String description,Integer status) {
        UsernamePasswordAuthenticationToken authentication =
                (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();

        UserDetailsImpl loginUser = (UserDetailsImpl) authentication.getPrincipal();
        User user = loginUser.getUser();
        String open_id = user.getOpenId();

        Map<String,String> map = new HashMap<>();

        QueryWrapper<Seller> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("open_id",open_id);

        Integer seller_id = sellerMapper.selectOne(queryWrapper).getSellerId();
        if(seller_id == null) {
            map.put("error_message","该商家不存在");
            return map;
        }
        QueryWrapper<Commodity> queryWrapper1 = new QueryWrapper<>();
        queryWrapper1.eq("seller_id",seller_id).eq("product_id",product_id);
        Commodity commodity = commodityMapper.selectOne(queryWrapper1);
        if(commodity == null) {
            map.put("error_message","该商家没有这个商品");
            return map;
        }
        Map<String,String> result = UploadUtil.upload(image);
        if(!result.get("error_message").equals("success")) {
            map.put("error_message","图片上传失败");
        }
        String photo = result.get("photo");
        Commodity new_commodity = new Commodity(
                commodity.getProductId(),
                product_name,
                photo,
                description,
                commodity.getSellerId(),
                sellercategory_id,
                status
        );
        commodityMapper.updateById(new_commodity);
        map.put("error_message","success");

        return map;
    }
}
