package com.takeout.backend.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Order {
    @TableId(type = IdType.AUTO)
    private Integer order_id;
    private Integer user_id;
    private Integer seller_id;
    private Date order_time;
    private String status;
    private Date created_at;
    private Date updated_at;

}
