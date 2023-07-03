package com.kuang.mapper;

import com.kuang.entity.LoginForm;
import com.kuang.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface UserMapper {

    @Select("select * from sys_user where username = #{username} and password = #{password}")
    User login(LoginForm loginForm);

    @Select("select * from sys_user where username = #{username}")
    User getInfoByName(String username);

    List<User> getUserByPage(int begin, int size,  String username, String nickname);

    int getCounts();

    int addUser(User user);

    int delUser(int id);

    int updateUser(User user);

}
