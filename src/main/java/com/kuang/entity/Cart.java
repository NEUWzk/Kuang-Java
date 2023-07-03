package com.kuang.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Cart {

    private Long id;
    private Integer count;
    private String createTime;
    private Long goodId;
    private String standard;
    private Long userId;

}
