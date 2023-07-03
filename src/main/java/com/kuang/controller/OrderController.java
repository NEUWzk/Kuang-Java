package com.kuang.controller;

import com.kuang.common.Result;
import com.kuang.entity.GoodDTO;
import com.kuang.entity.GoodDetail;
import com.kuang.entity.Order;
import com.kuang.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;

@CrossOrigin
@RestController
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/api/order")
    public Result commitOrder(@RequestBody Order order, HttpServletRequest request)
    {
        String token = request.getHeader("token");
        return Result.success(orderService.commitOrder(order,token));
    }

    @GetMapping("/api/order/paid/{number}")
    public Result PaidOrder(@PathVariable String number)
    {
        int res = orderService.PaidOrder(number);
        return Result.success(res);
    }

    @RequestMapping("/api/order/userid/{userid}")
    public Result getOrderByID(@PathVariable int userid)
    {
        return Result.success(orderService.getOrderByID(userid));
    }

    @RequestMapping("/api/order/page")
    public Result getAllOrder(@RequestParam(required = false)  Integer pageNum,
                              @RequestParam(required = false)  Integer pageSize,
                              @RequestParam(required = false)  Integer orderNo,
                              @RequestParam(required = false)  String state)
    {
        List<Order> orderList = orderService.getAllOrder(pageNum,pageSize,orderNo,state);
        int count = orderService.getCount();
        HashMap<String, Object> map = new HashMap<>();
        map.put("records",orderList);
        map.put("total",count);
        return Result.success(map);

    }

    @RequestMapping("/api/order/orderNo/{orderNo}")
    public Result getOrderDetail(@PathVariable String orderNo)
    {
        List<GoodDetail> detailList = orderService.selectByOrderNo(orderNo);
        return Result.success(detailList);
    }

    @GetMapping("/api/order/delivery/{orderNo}")
    public Result Deliver(@PathVariable String orderNo)
    {
        int res = orderService.Deliver(orderNo);
        return Result.success(res);
    }


}
