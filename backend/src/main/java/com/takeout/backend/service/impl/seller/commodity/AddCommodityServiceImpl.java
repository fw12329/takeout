package com.takeout.backend.service.impl.seller.commodity;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.takeout.backend.mapper.*;
import com.takeout.backend.pojo.*;
import com.takeout.backend.service.impl.utils.UserDetailsImpl;
import com.takeout.backend.service.seller.commodity.AddCommodityService;
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
public class AddCommodityServiceImpl implements AddCommodityService {

    @Autowired
    private SellerMapper sellerMapper;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private SpecMapper specMapper;

    @Autowired
    private SpecsMapper specsMapper;

    @Autowired
    private CommodityMapper commodityMapper;

    @Autowired
    private CommodityCategoryMapper commodityCategoryMapper;


    @Override
    @Transactional
    public Map<String, String> addCommodity(MultipartFile image,
                                            String product_name,
                                            Integer status,
                                            String desc,
                                            String specs,
                                            String category) throws JsonProcessingException {
        Map<String,String> data = new HashMap<>();
        UsernamePasswordAuthenticationToken authentication =
                (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();

        UserDetailsImpl loginUser = (UserDetailsImpl) authentication.getPrincipal();
        User user = loginUser.getUser();
        String open_id = user.getOpenId();


        QueryWrapper<Seller> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("open_id",open_id);
        Seller seller = sellerMapper.selectOne(queryWrapper);

        if(seller == null) {
            data.put("error_message","您还没有创建商家");
            return data;
        }

        Integer seller_id = seller.getSellerId();
        Map<String,String> result = UploadUtil.upload(image);
        if(!result.get("error_message").equals("success")) {
            data.put("error_message","商品图片上传失败");
            return data;
        }
        String photo = result.get("photo");
        Commodity commodity = new Commodity(null,product_name,photo,desc,seller_id,null,status);
        commodityMapper.insert(commodity);
        Integer product_id = commodity.getProductId();
        ArrayList<Map<String,Object>> spec = objectMapper.readValue(specs,ArrayList.class);
        List<Object> cate = objectMapper.readValue(category,ArrayList.class);
        for(int i = 0;i < cate.size();i++) {
            Commoditycategory commodityCategory = new Commoditycategory(null,cate.get(i).toString(),commodity.getProductId(),seller_id);
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
        data.put("error_message","success");
        return data;
    }
}
