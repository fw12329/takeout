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
    private String image;
    private String description;
    private Integer sellerId;
    private Integer sellercategoryId;
    private Integer status;


}
