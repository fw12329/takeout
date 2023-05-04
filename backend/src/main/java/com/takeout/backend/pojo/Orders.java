package com.takeout.backend.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Orders {
    @TableId(type = IdType.AUTO)
    private Integer orderId;
    private Double totalPrice;
    private Date createdAt;
    private Date updatedAt;
    private String contactName;
    private String contactPhone;
    private String contactAddress;
    private Integer status;
    private String isPlay;
    private Integer sellerId;
    private String openId;

}
