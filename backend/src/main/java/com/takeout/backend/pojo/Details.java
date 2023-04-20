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
public class Details {
    @TableId(type = IdType.AUTO)
    private Integer order_item_id;
    private Integer order_id;
    private Integer product_id;
    private Integer quantity;
    private Double price;
    private Date created_at;
    private Integer user_id;
    private Integer seller_id;

}
