package com.kuang.mapper;

import com.kuang.entity.GoodDetail;
import com.kuang.entity.Order;
import com.kuang.entity.OrderGoods;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Mapper
@Repository
public interface OrderMapper {

    void commitOrder(Order order);

    @MapKey("id")
    List<Map<String, Object>> getOrderByID(int userid);

    void payOrder(String number);

    int selectIDByOrderNum(String number);

    int updateStoreInfo(int goodId, int count, String standard);

    void insertOG(OrderGoods orderGoods);

    Long queryOrderId();

    List<Order> getAllOrder(Integer begin, Integer size, Integer orderNo, String state);

    @Select("select count(*) from t_order")
    int getCount();

    List<GoodDetail> selectByOrderNo(String orderNo);

    @Update("update t_order set state = '已发货' where order_no = #{orderNo}")
    int Deliver(String orderNo);

}
