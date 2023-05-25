package com.takeout.backend.controller.user;


import com.takeout.backend.pojo.Seller;
import com.takeout.backend.service.user.GetSearchListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class GetSearchListController {
    @Autowired
    private GetSearchListService getSearchListService;

    @PostMapping("/user/getSearchList/")
    public List<Seller> getRecommendlist(@RequestBody Map<String,String> data) {
        Integer page = Integer.parseInt(data.get("page"));
        String keyword = data.get("keyword");
        return getSearchListService.GetSearchList(page,keyword);
    }
}
