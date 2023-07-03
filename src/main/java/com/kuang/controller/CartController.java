package com.kuang.controller;

import cn.hutool.core.date.DateUtil;
import com.kuang.common.Result;
import com.kuang.entity.Cart;
import com.kuang.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;

@CrossOrigin
@RestController
public class CartController {

    @Autowired
    private CartService cartService;

    @RequestMapping("/api/cart")
    public Result addToCart(@RequestBody Cart cart)
    {
        cart.setCreateTime(DateUtil.now());  //设置当前时间戳
        int res = cartService.addToCart(cart);
        return Result.success(res);
    }


    @GetMapping("/api/cart/userid/{userId}")
    public Result selectByUserId(@PathVariable Long userId) {
        return Result.success(cartService.selectByUserId(userId)) ;
    }

}
