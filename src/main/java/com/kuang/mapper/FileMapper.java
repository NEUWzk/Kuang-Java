package com.kuang.mapper;

import com.kuang.entity.MyFile;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface FileMapper {

    int getCount();

    List<MyFile> getAllFiles(int begin, int size, String fileName);

    int delFile(int id);

    List<MyFile> queryByName(String md5);

    void insert(MyFile myFile);

}
