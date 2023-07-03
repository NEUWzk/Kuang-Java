package com.kuang.interceptor;

import com.auth0.jwt.exceptions.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kuang.common.Constants;
import com.kuang.common.RedisConstants;
import com.kuang.entity.User;
import com.kuang.service.UserService;
import com.kuang.utils.JWTUtils;
import com.kuang.utils.ServiceException;
import com.kuang.utils.UserHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Component
public class JwtInterceptor implements HandlerInterceptor {

    @Resource
    RedisTemplate<String, User> redisTemplate;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader("token"); //从消息头获取JWT-token
        Map<String, Object> map = new HashMap<>();
        //如果不是映射到方法，直接通过
        if(!(handler instanceof HandlerMethod)){
            return true;
        }
        //验证是否有token
        if(!StringUtils.hasLength(token)){
            throw new ServiceException(Constants.TOKEN_ERROR,"token失效,请重新登陆");
        }

        User u = redisTemplate.opsForValue().get(RedisConstants.USER_TOKEN_KEY + token);
        if (u == null) {
           return false;
        }

        //验证token是否正确：
        try {
            JWTUtils.verify(token);  //验证成功，直接放行
            return true;
        } catch (JWTVerificationException e) {
            throw new ServiceException(Constants.TOKEN_ERROR,"token验证失败，请重新登陆");
        }

    }
}