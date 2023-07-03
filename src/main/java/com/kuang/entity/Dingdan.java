package com.kuang.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Dingdan {

    private Long id;
    private Integer count;
    private String createTime;
    private Long goodId;
    private String standard;
    private Long userId;

    private String imgs;
    private double price;
    private String name;
    private double discount;
    private int store;

}
