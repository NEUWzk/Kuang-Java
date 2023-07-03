package com.kuang.controller;

import com.kuang.common.Result;
import com.kuang.entity.Category;
import com.kuang.entity.Good;
import com.kuang.entity.GoodDTO;
import com.kuang.service.GoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@CrossOrigin
public class GoodController {

    @Autowired
    private GoodService goodService;

    @RequestMapping("/api/good")
    public Result getAll()
    {
        return Result.success(goodService.getAllGoods());
    }

    @RequestMapping("/api/good/{id}")
    public Result getGoodByID(@PathVariable int id)
    {
        return Result.success(goodService.getGoodByID(id));
    }

    @RequestMapping("/api/good/standard/{id}")
    public Result getGoodStandard(@PathVariable int id)
    {
        return Result.success(goodService.getGoodStandard(id));
    }

    @RequestMapping("/api/good/page")
    public Result getAllCategory(@RequestParam(required = false)  int pageNum,
                                 @RequestParam(required = false)  int pageSize,
                                 @RequestParam(required = false)  String searchText)
    {
        List<GoodDTO> goodList = goodService.getAllCategory(pageNum, pageSize, searchText);
        int count = goodService.getCount();
        HashMap<String, Object> map = new HashMap<>();
        map.put("records",goodList);
        map.put("total",count);
        return Result.success(map);
    }


    @GetMapping("/api/good/fullPage")
    public Result getAllGoods(@RequestParam(required = false)  int pageNum,
                              @RequestParam(required = false)  int pageSize,
                              @RequestParam(required = false)  String searchText)
    {
        List<Good> goodList = goodService.getGoods(pageNum, pageSize, searchText);
        int count = goodService.getCount();
        HashMap<String, Object> map = new HashMap<>();
        map.put("records",goodList);
        map.put("total",count);
        return Result.success(map);
    }

    @RequestMapping ("/api/good/recommend")
    public Result editRecommend(@RequestParam Long id, @RequestParam Boolean isRecommend)
    {
        int res = goodService.editRecommend(id,isRecommend);
        return Result.success();
    }

    @RequestMapping("/api/category")
    public Result getAllClassify()
    {
        HashMap<String, Object> map = new HashMap<>();
        List<Category> CateList = goodService.getAllClassify();
        return Result.success(CateList);
    }

}
