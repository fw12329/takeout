package com.takeout.backend.service.impl.seller;

import com.takeout.backend.mapper.CommodityMapper;
import com.takeout.backend.pojo.Commodity;
import com.takeout.backend.service.seller.AddCommodityService;
import com.takeout.backend.utils.UploadUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class AddCommodityServiceImpl implements AddCommodityService {

    @Autowired
    private CommodityMapper commodityMapper;

    @Override
    public Map<String, String> addCommodity(MultipartFile imageFile, String product_name, String description, Double price, Integer stock,Integer seller_id) {
        Map<String,String> map = new HashMap<>();
        Map<String,String> result = UploadUtil.upload(imageFile);
        if(!result.get("error_message").equals("success")) {
            map.put("error_message","图片上传失败");
        }
        String photo = result.get("photo");
        try {
            Commodity commodity = new Commodity(null,product_name,description,price,stock,seller_id,new Date(),new Date(),photo);
            commodityMapper.insert(commodity);
            map.put("error_message","success");
            return map;
        } catch (Exception e) {
            map.put("error_message",e.getMessage());
            return map;
        }

    }
}
