package com.kuang.service;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.RandomUtil;
import com.alibaba.fastjson.JSON;

import com.kuang.common.RedisConstants;
import com.kuang.common.Result;
import com.kuang.entity.*;
import com.kuang.mapper.CartMapper;
import com.kuang.mapper.OrderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class OrderService {

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private CartMapper cartMapper;

    @Resource
    private RedisTemplate<String, User> redisTemplate;

    public String commitOrder(Order order,String token) {
        User u = redisTemplate.opsForValue().get(RedisConstants.USER_TOKEN_KEY + token);
        order.setUserId(u.getId());
        String orderNo = DateUtil.format(new Date(), "yyyyMMddHHmmss") + RandomUtil.randomNumbers(6);
        order.setOrderNo(orderNo);

        Date now = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String nowStr = sdf.format(now);
        order.setCreateTime(nowStr);

        orderMapper.commitOrder(order);  //在这里得到id
        Long orderId = orderMapper.queryOrderId();  //倒叙查找最新的googId

        OrderGoods orderGoods = new OrderGoods();
        orderGoods.setOrderId(orderId);
        String goods = order.getGoods();
        List<OrderItem> orderItems = JSON.parseArray(goods, OrderItem.class);
        for (OrderItem orderItem : orderItems) {
            long good_id = orderItem.getId();
            String standard = orderItem.getStandard();
            int num = orderItem.getNum();
            orderGoods.setGoodId(good_id);
            orderGoods.setCount(num);
            orderGoods.setStandard(standard);
            //插入到order_good表
            orderMapper.insertOG(orderGoods);
        }
        cartMapper.removeById(order.getCartId());
        return orderNo;
    }


    public int PaidOrder(String number) {   //支付成功之后采取的操作
        orderMapper.payOrder(number);  //将状态改成“已支付”
        return 1;
    }

    public List<Map<String, Object>> getOrderByID(int userid) {
        return orderMapper.getOrderByID(userid);
    }

    public List<Order> getAllOrder(Integer pageNum, Integer pageSize,
                                   Integer orderNo, String state) {
        int page = Math.max(pageNum,1);
        int size = pageSize;
        int begin = (page-1)*size;
        return orderMapper.getAllOrder(begin,size,orderNo,state);
    }

    public int getCount() {
        return orderMapper.getCount();
    }

    public List<GoodDetail> selectByOrderNo(String orderNo) {
        return orderMapper.selectByOrderNo(orderNo);
    }

    public int Deliver(String orderNo) {
        return orderMapper.Deliver(orderNo);
    }
}
