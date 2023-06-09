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
    private Integer id;
    private Integer orderId;
    private Integer productId;
    private Double specPrice;
    private Integer quantity;

}
