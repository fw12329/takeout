package com.takeout.backend.controller.user;


import com.takeout.backend.pojo.Seller;
import com.takeout.backend.service.user.GetRecommendListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class GetRecommendListController {

    @Autowired
    private GetRecommendListService getRecommendListService;

    @PostMapping("/user/getrecommendlist/")
    public List<Seller> getRecommendList(@RequestBody Map<String,String> data) {
        Integer page = Integer.parseInt(data.get("page"));
        return getRecommendListService.getRecommendList(page);
    }
}
