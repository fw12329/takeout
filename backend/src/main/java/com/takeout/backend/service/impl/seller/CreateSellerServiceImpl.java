package com.takeout.backend.service.impl.seller;

import com.takeout.backend.mapper.SellerMapper;
import com.takeout.backend.pojo.Seller;
import com.takeout.backend.pojo.User;
import com.takeout.backend.service.impl.utils.UserDetailsImpl;
import com.takeout.backend.service.seller.CreateSellerService;
import com.takeout.backend.utils.UploadUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class CreateSellerServiceImpl implements CreateSellerService {

    @Autowired
    private SellerMapper sellerMapper;


    @Override
    @Transactional
    public Map<String, String> create(MultipartFile photo,
                                      String seller_name,
                                      String seller_desc,
                                      String seller_address,
                                      Integer category_id,
                                      String license_number) {
        UsernamePasswordAuthenticationToken authentication =
                (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();

        UserDetailsImpl loginUser = (UserDetailsImpl) authentication.getPrincipal();
        User user = loginUser.getUser();
        String open_id = user.getOpenId();
        Map<String,String> map = new HashMap<>();
        Map<String,String> result = UploadUtil.upload(photo);
        if(!result.get("error_message").equals("success")) {
            map.put("error_message","图片上传失败");
            return map;
        }
        if(seller_name.trim().length() < 1) {
            map.put("error_message","商家名不能为空");
            return map;
        }
        String image = result.get("photo");
        Seller seller = new Seller(null,seller_name,seller_desc,seller_address,open_id,license_number,image,category_id,new Date(),new Date());
        sellerMapper.insert(seller);
        map.put("error_message","success");
        return map;
    }
}
