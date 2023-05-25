package com.takeout.backend.controller.user.seller;

import com.takeout.backend.service.user.seller.GetSellerCategoryListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class GetSellerCategoryListController {

    @Autowired
    private GetSellerCategoryListService getSellerCategoryListService;

    @PostMapping ("/user/seller/getsellercategorylist/")
    public List<Map<String,Object>> getSellercategoryList(@RequestBody Map<String,String> data) {
        Integer seller_id = Integer.parseInt(data.get("seller_id"));
        return getSellerCategoryListService.getSellerCategoryList(seller_id);
    }
}
