package com.takeout.backend.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Spec {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private String specName;
    private Double specPrice;
    private Integer stock;
    private Integer specsId;
}
