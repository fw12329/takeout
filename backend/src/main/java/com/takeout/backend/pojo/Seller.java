package com.takeout.backend.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Seller {
    @TableId(type = IdType.AUTO)
    private Integer sellerId;
    private String sellerName;
    private String sellerDesc;
    private String sellerAddress;
    private String openId;
    private String licenseNumber;
    private String sellerPhoto;
    private Integer categoryId;
    private Date createdAt;
    private Date updatedAt;
}
