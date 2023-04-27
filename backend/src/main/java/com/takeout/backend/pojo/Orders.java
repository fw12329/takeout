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
    private String openId;
    private Integer sellerId;
    private String status;
    private Date createdAt;
    private Date updatedAt;

}
