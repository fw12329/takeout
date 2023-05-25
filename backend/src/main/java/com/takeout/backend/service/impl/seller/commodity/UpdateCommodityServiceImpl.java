package com.takeout.backend.service.impl.seller.commodity;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.takeout.backend.mapper.*;
import com.takeout.backend.pojo.*;
import com.takeout.backend.service.impl.utils.UserDetailsImpl;
import com.takeout.backend.service.seller.commodity.UpdateCommodityService;
import com.takeout.backend.utils.UploadUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UpdateCommodityServiceImpl implements UpdateCommodityService {

    @Autowired
    private SellerMapper sellerMapper;

    @Autowired
    private CommodityMapper commodityMapper;

    @Autowired
    private SpecsMapper specsMapper;

    @Autowired
    private SpecMapper specMapper;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private CommodityCategoryMapper commodityCategoryMapper;


    @Override
    @Transactional
    public Map<String, String> updateCommodity(Integer product_id,
                                               MultipartFile image,
                                               String product_name,
                                               Integer sellercategory_id,
                                               String description,
                                               String specs,
                                               Integer status,
                                               String category) throws Exception {
        UsernamePasswordAuthenticationToken authentication =
                (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();

        UserDetailsImpl loginUser = (UserDetailsImpl) authentication.getPrincipal();
        User user = loginUser.getUser();
        String open_id = user.getOpenId();

        Map<String,String> data = new HashMap<>();

        QueryWrapper<Seller> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("open_id",open_id);

        Seller seller = sellerMapper.selectOne(queryWrapper);
        if(seller == null) {
            data.put("error_message","该商家不存在");
            return data;
        }

        QueryWrapper<Commodity> queryWrapper1 = new QueryWrapper<>();
        queryWrapper1.eq("seller_id",seller.getSellerId()).eq("product_id",product_id);
        Commodity commodity = commodityMapper.selectOne(queryWrapper1);
        if(commodity == null) {
            data.put("error_message","该商家没有这个商品");
            return data;
        }
        Map<String,String> result = UploadUtil.upload(image);
        if(!result.get("error_message").equals("success")) {
            data.put("error_message","图片上传失败");
            return data;
        }

        ArrayList<Map<String,Object>> spec = objectMapper.readValue(specs,ArrayList.class);
        QueryWrapper<Commoditycategory> commodityCategoryQueryWrapper = new QueryWrapper<>();
        commodityCategoryQueryWrapper.eq("product_id",commodity.getProductId()).eq("seller_id",seller.getSellerId());
        commodityCategoryMapper.delete(commodityCategoryQueryWrapper);
        List<Object> cate = objectMapper.readValue(category,ArrayList.class);
        for(int i = 0;i < cate.size();i++) {
            Commoditycategory commodityCategory = new Commoditycategory(null,cate.get(i).toString(),commodity.getProductId(),seller.getSellerId());
            commodityCategoryMapper.insert(commodityCategory);
        }
        for(int i = 0;i < spec.size();i++) {
            Map<String,Object> spec_details = spec.get(i);
            String specs_name = spec_details.get("specs_name").toString();
            Specs specs1 = new Specs(null,specs_name,product_id);
            specsMapper.insert(specs1);
            ArrayList<Map<Object,Object>> list = (ArrayList<Map<Object,Object>>)spec_details.get("spec_list");
            for(int j = 0;j < list.size();j++) {
                Map<Object,Object> map = list.get(j);
                String spec_name = map.get("spec_name").toString();
                Double spec_price = Double.parseDouble(map.get("spec_price").toString());
                Integer stock = Integer.parseInt(map.get("stock").toString());
                Spec spec2 = new Spec(null,spec_name,spec_price,stock,specs1.getId());
                specMapper.insert(spec2);
            }

        }
        QueryWrapper<Specs> queryWrapper2 = new QueryWrapper<>();
        queryWrapper2.eq("product_id",commodity.getProductId());
        List<Specs> remove_specs_list = specsMapper.selectList(queryWrapper2);
        for(int i = 0;i < remove_specs_list.size();i++) {
            QueryWrapper<Spec> queryWrapper3 = new QueryWrapper<>();
            queryWrapper3.eq("specs_id",remove_specs_list.get(i).getId());
            specMapper.delete(queryWrapper3);
            specsMapper.deleteById(remove_specs_list.get(i).getId());
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
        data.put("error_message","success");
        return data;
    }
}
