package com.kuang.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GoodDetail {
    private String img;
    private Long goodId;
    private String goodName;
    private String standard;
    private double price;
    private Double discount;
    private int count;

}
