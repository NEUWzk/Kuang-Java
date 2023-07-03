package com.kuang.mapper;

import com.kuang.entity.Carousel;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface CarouselMapper {

    List<Carousel> AllCarousel();

}
