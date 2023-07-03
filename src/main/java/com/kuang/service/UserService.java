package com.kuang.service;

import cn.hutool.core.bean.BeanUtil;
import com.kuang.common.Constants;
import com.kuang.common.RedisConstants;
import com.kuang.entity.LoginForm;
import com.kuang.entity.User;
import com.kuang.entity.UserDTO;
import com.kuang.mapper.UserMapper;
import com.kuang.utils.JWTUtils;
import com.kuang.utils.ServiceException;
import com.kuang.utils.UserHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    @Resource
    RedisTemplate<String,User> redisTemplate;

    public UserDTO login(LoginForm loginForm) {
        User user = userMapper.login(loginForm);
        if(user == null)
        {
            throw new ServiceException(Constants.CODE_403,"用户名或密码错误");
        }else{  //如果匹配成功
            HashMap<String, String> map = new HashMap<>();
            map.put("userId",user.getId().toString());
            String token = JWTUtils.getToken(map);  //登录成功后生成token
            redisTemplate.opsForValue().set(RedisConstants.USER_TOKEN_KEY + token,user);  //存到redis中
            redisTemplate.expire(RedisConstants.USER_TOKEN_KEY +token,
                    RedisConstants.USER_TOKEN_TTL, TimeUnit.MINUTES);

            UserDTO userDTO = BeanUtil.copyProperties(user,UserDTO.class);

            UserHolder.saveUser(user);  //放入userThreadLocal

            //设置token
            userDTO.setToken(token);
            return userDTO;
        }

    }

    public User getInfoByName(String username) {
        return userMapper.getInfoByName(username);
    }

    public List<User> getUserByPage(int pageNum, int pageSize, String username, String nickname) {
        int page = Math.max(1,pageNum);
        int size = pageSize;
        int begin = (page -1) * size;
        return userMapper.getUserByPage(begin,size,username,nickname);
    }

    public int getCounts() {
        return userMapper.getCounts();
    }

    public int addUser(User user) {
        return userMapper.addUser(user);
    }

    public int delUser(int id) {
        return userMapper.delUser(id);
    }

    public int updateUser(User user) {
        return userMapper.updateUser(user);
    }
}
