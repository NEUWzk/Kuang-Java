package com.kuang.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Good {

    private Long id;
    private String name;
    private String description;
    private Double discount;  //折扣
    private Integer sales; //销量
    private BigDecimal saleMoney;  //销售额
    private Long categoryId; //分类id
    private String imgs;
    private String createTime;
    private Boolean recommend;  //是否推荐
    private Boolean isDelete;  //是否删除
    private BigDecimal price;  //原价
}
