package com.kuang.service;

import com.kuang.entity.Cart;
import com.kuang.mapper.CartMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class CartService {

    @Autowired
    private CartMapper cartMapper;

    public int addToCart(Cart cart) {
        return cartMapper.addToCart(cart);
    }

    public List<Map<String,Object>> selectByUserId(Long userId) {
        return cartMapper.selectByUserId(userId);
    }
}
