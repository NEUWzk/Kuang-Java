package com.kuang.mapper;

import com.kuang.entity.Cart;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Mapper
@Repository
public interface CartMapper {

    int addToCart(Cart cart);

    @MapKey("id")
    List<Map<String, Object>> selectByUserId(Long userId);

    void removeById(int cartId);

}
