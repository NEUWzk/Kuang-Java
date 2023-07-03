package com.kuang.service;

import com.kuang.entity.Carousel;
import com.kuang.mapper.CarouselMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarouselService {

    @Autowired
    private CarouselMapper carouselMapper;


    public List<Carousel> AllCarousel() {
        return carouselMapper.AllCarousel();
    }
}
