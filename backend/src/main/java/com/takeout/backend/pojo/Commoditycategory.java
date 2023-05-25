package com.takeout.backend.pojo;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Commoditycategory {

    @TableId(type = IdType.AUTO)
    private Integer id;
    private String name;
    private Integer productId;
    private Integer sellerId;
}
