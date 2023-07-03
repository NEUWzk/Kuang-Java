package com.kuang.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MyDingdan extends Order{
    private String imgs;
    private Long goodId;
    private String goodName;
    private int count;
    private String standard;
}
