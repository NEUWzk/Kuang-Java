package com.kuang.mapper;

import com.kuang.entity.Avatar;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface AvatarMapper {

    @Select("select * from avatar limit #{index},#{pageSize}")
    List<Avatar> selectPage(int index, int pageSize);

    @Select("select count(*) from avatar")
    int getTotal();

}
