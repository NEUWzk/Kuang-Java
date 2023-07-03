package com.kuang.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Carousel {

    private Integer showOrder;
    private Long id;
    private Long goodId;
    private String img;
}
