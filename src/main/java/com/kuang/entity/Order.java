package com.kuang.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("t_order")
public class Order {

    private Long id;
    private String orderNo;
    private BigDecimal totalPrice;
    private int userId;
    private String linkUser;
    private String linkPhone;
    private String linkAddress;
    private String state;

    private String createTime;
    private String goods;
    private int cartId;

}
