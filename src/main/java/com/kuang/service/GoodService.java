package com.kuang.service;

import com.alibaba.fastjson.JSON;
import com.kuang.entity.Category;
import com.kuang.entity.Good;
import com.kuang.entity.GoodDTO;
import com.kuang.entity.GoodStandard;
import com.kuang.mapper.GoodMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class GoodService {

    @Autowired
    private GoodMapper goodMapper;


    public List<GoodDTO> getAllGoods() {
        return goodMapper.getAllGoods();
    }

    public Good getGoodByID(int id) {
        return goodMapper.getGoodByID(id);
    }

    public String getGoodStandard(int id) {

        List<GoodStandard> standard = goodMapper.getGoodStandard(id);
        return JSON.toJSONString(standard);
    }

    public List<GoodDTO> getAllCategory(int pageNum, int pageSize,String searchText) {
        int page = Math.max(pageNum, 1);
        int size = pageSize;
        int begin = (page - 1) * size;
        return goodMapper.getAllCategory(begin,size,searchText);
    }

    public int getCount() {
        return goodMapper.getCount();
    }

    public List<Good> getGoods(int pageNum, int pageSize, String searchText) {
        int page = Math.max(pageNum,1);
        int size = pageSize;
        int begin = (page-1)*size;
        return goodMapper.getGoods(begin,size,searchText);
    }

    public int editRecommend(Long id, Boolean recommend) {
        return goodMapper.editRecommend(id,recommend);
    }

    public List<Category> getAllClassify() {
        return goodMapper.getAllClassify();
    }
}
