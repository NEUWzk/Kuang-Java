package com.kuang.mapper;

import com.kuang.entity.Category;
import com.kuang.entity.Good;
import com.kuang.entity.GoodDTO;
import com.kuang.entity.GoodStandard;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Mapper
@Repository
public interface GoodMapper {


    List<GoodDTO> getAllGoods();

    Good getGoodByID(int id);

    List<GoodStandard> getGoodStandard(int id);

    List<GoodDTO> getAllCategory(int begin, int size, String searchText);

    int getCount();

    List<Good> getGoods(int begin, int size, String searchText);

    int editRecommend(Long id, Boolean recommend);

    List<Category> getAllClassify();

}
