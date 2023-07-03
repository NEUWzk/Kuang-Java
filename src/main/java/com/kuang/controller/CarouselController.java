package com.kuang.controller;

import com.kuang.common.Result;
import com.kuang.service.CarouselService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class CarouselController {

    @Autowired
    private CarouselService carouselService;

    @RequestMapping("/api/carousel")
    public Result AllCarousel()
    {
        return Result.success(carouselService.AllCarousel());
    }
}
