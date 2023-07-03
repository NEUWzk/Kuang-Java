package com.kuang.controller;

import com.kuang.common.Result;
import com.kuang.service.IconService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class IconController {

    @Autowired
    private IconService iconService;

    @RequestMapping("/api/icon")
    public Result queryAllIcons()
    {
        return Result.success(iconService.queryAllIcons());
    }
}
