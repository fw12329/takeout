package com.takeout.backend.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.takeout.backend.pojo.Orders;
import com.takeout.backend.pojo.Seller;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.session.RowBounds;

import java.util.List;

@Mapper
public interface OrdersMapper extends BaseMapper<Orders> {
    @Select("SELECT s.* " +
            "FROM seller s " +
            "LEFT JOIN commoditycategory cc ON s.seller_id = cc.seller_id " +
            "LEFT JOIN (SELECT seller_id, COUNT(*) AS order_count FROM orders GROUP BY seller_id) o " +
            "ON s.seller_id = o.seller_id " +
            "WHERE (s.seller_name LIKE '%烧烤%' OR cc.name = '烧烤') " +
            "ORDER BY IFNULL(o.order_count, 0) DESC")
    List<Seller> getSellersWithOrders(RowBounds rowBounds);
}
