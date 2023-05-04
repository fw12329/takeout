package com.takeout.backend.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Detailsspec {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private Integer specId;
    private Integer specsId;
    private Integer detailsId;
}
