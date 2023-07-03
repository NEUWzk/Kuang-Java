package com.kuang.service;

import com.kuang.entity.Icon;
import com.kuang.mapper.IconMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IconService {

    @Autowired
    private IconMapper iconMapper;


    public List<Icon> queryAllIcons() {
        return iconMapper.queryAllIcons();
    }
}
