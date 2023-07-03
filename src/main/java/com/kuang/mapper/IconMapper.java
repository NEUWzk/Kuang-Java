package com.kuang.mapper;

import com.kuang.entity.Icon;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface IconMapper {

    List<Icon> queryAllIcons();

}
