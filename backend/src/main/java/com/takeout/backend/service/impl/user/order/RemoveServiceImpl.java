package com.takeout.backend.service.impl.user.order;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.takeout.backend.mapper.CommodityMapper;
import com.takeout.backend.mapper.DetailsMapper;
import com.takeout.backend.pojo.Commodity;
import com.takeout.backend.pojo.Details;
import com.takeout.backend.pojo.User;
import com.takeout.backend.service.user.order.RemoveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class RemoveServiceImpl implements RemoveService {
    @Autowired
    private CommodityMapper commodityMapper;

    @Autowired
    private DetailsMapper detailsMapper;

    @Override
    public Map<String, String> remove(Map<String, String> data) {
        Map<String,String> map = new HashMap<>();
        Integer product_id = Integer.parseInt(data.get("product_id"));
        Integer seller_id = Integer.parseInt(data.get("seller_id"));
        User user = new User(1,"QS","18965009795",new Date(),new Date());

        QueryWrapper<Details> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id",user.getUser_id()).eq("product_id",product_id).eq("seller_id",seller_id);
        Details details = detailsMapper.selectOne(queryWrapper);

        QueryWrapper<Commodity> queryWrapper1 = new QueryWrapper<>();
        queryWrapper1.eq("product_id",product_id).eq("seller_id",seller_id);
        Commodity commodity = commodityMapper.selectOne(queryWrapper1);

        if(detailsMapper.selectOne(queryWrapper) != null) {
            UpdateWrapper<Details> updateWrapper1 = new UpdateWrapper<>();
            Integer quantity = details.getQuantity() - 1;
            updateWrapper1.eq("user_id",user.getUser_id()).eq("product_id",product_id).eq("seller_id",seller_id).set("quantity",quantity);
            detailsMapper.update(null,updateWrapper1);
            if(quantity < 1) {
                QueryWrapper<Details> queryWrapper2 = new QueryWrapper<>();
                queryWrapper2.eq("user_id",user.getUser_id()).eq("product_id",product_id).eq("seller_id",seller_id);
                detailsMapper.delete(queryWrapper2);
            } else {
                map.put("error_message","该商家当前商品库存不足");
                return map;
            }
            UpdateWrapper<Commodity> updateWrapper = new UpdateWrapper<>();
            Integer stock = commodity.getStock() + 1;
            updateWrapper.eq("product_id",product_id).eq("seller_id",seller_id).set("stock",stock);
            commodityMapper.update(null,updateWrapper);

            map.put("error_message","success");

        }



        
        return map;
    }
}
