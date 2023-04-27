package com.takeout.backend.pojo;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class Commodity {
    @TableId(type = IdType.AUTO)
    private Integer productId;
    private String productName;
    private String description;
    private Double price;
    private Integer stock;
    private Integer sellerId;
    private Date createdAt;
    private Date updatedAt;
    private String photo;


}
