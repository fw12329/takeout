package com.takeout.backend.controller.user.seller;


import com.takeout.backend.pojo.Seller;
import com.takeout.backend.service.user.seller.GetSellerListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class GetSellerListController {

    @Autowired
    private GetSellerListService getSellerListService;

    @PostMapping("/user/seller/getsellerlist/")
    public List<Seller> getsellerlist(@RequestBody Map<String,String> data) {
        Integer page = Integer.parseInt(data.get("page"));
        return getSellerListService.getSellerlist(page);
    }
}
