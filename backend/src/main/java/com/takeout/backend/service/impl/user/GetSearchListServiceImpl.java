package com.takeout.backend.service.impl.user;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.takeout.backend.mapper.OrdersMapper;
import com.takeout.backend.mapper.SellerMapper;
import com.takeout.backend.pojo.Orders;
import com.takeout.backend.pojo.Seller;
import com.takeout.backend.service.user.GetSearchListService;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class GetSearchListServiceImpl implements GetSearchListService {
    @Autowired
    private SellerMapper sellerMapper;

    @Autowired
    private OrdersMapper ordersMapper;

    @Override
    public List<Seller> GetSearchList(Integer page, String keyword) {
        QueryWrapper<Seller> sellerQueryWrapper = new QueryWrapper<>();
        sellerQueryWrapper.like("seller_name", keyword).or()
                .inSql("seller_id", "SELECT seller_id FROM commoditycategory WHERE name = '\" + keyword + \"'");
        List<Seller> sellers = sellerMapper.selectList(sellerQueryWrapper);
        List<Integer> sellerIds = sellers.stream()
                .map(Seller::getSellerId)
                .collect(Collectors.toList());
        QueryWrapper<Orders> ordersQueryWrapper = new QueryWrapper<>();
        ordersQueryWrapper.select("seller_id", "COUNT(*) as order_count");
        ordersQueryWrapper.in("seller_id", sellerIds);
        ordersQueryWrapper.groupBy("seller_id");
        ordersQueryWrapper.orderByDesc("order_count");


        int offset = (page - 1) * 10;
        RowBounds rowBounds = new RowBounds(offset, 10);

        List<Seller> sellersList = ordersMapper.getSellersWithOrders(rowBounds);
        long total = sellersList.size();

        IPage<Seller> pages = new Page<>(page, 10,total);



        return pages.setRecords(sellersList).getRecords();
    }
}
