package com.kuang.controller;

import com.kuang.common.RedisConstants;
import com.kuang.common.Result;
import com.kuang.entity.LoginForm;
import com.kuang.entity.User;
import com.kuang.entity.UserDTO;
import com.kuang.service.UserService;
import com.kuang.utils.JWTUtils;
import com.kuang.utils.TokenUtils;
import com.kuang.utils.UserHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;

@RestController
@CrossOrigin
public class UserController {

    @Autowired
    private UserService userService;

    @Resource
    RedisTemplate<String,User> redisTemplate;

    @RequestMapping("/login")
    public Result UserLogin(@RequestBody LoginForm loginForm)
    {
        UserDTO user = userService.login(loginForm);
        return Result.success(user);
    }

    @RequestMapping("/role")
    public Result getUserRole(HttpServletRequest request)
    {
        String token = request.getHeader("token");
        User user = redisTemplate.opsForValue().get(RedisConstants.USER_TOKEN_KEY + token);
        return Result.success(user.getRole());
    }

    @GetMapping("/userinfo/{username}")
    public Result getuserinfo(@PathVariable String username)
    {
        User u = userService.getInfoByName(username);
        return Result.success(u);
    }


    @GetMapping("/userid")
    public long getUserId(HttpServletRequest request) {
        String token = request.getHeader("token");
        User user = redisTemplate.opsForValue().get(RedisConstants.USER_TOKEN_KEY + token);
        return user.getId();
    }

    @RequestMapping("/user/page")
    public Result getUserByPage(@RequestParam(required = false) int pageNum,
                                @RequestParam(required = false) int pageSize,

                                @RequestParam(required = false) String username,
                                @RequestParam(required = false) String nickname)
    {
        List<User> userList = userService.getUserByPage(pageNum,pageSize,username,nickname);
        HashMap<String, Object> map = new HashMap<>();
        int count = userService.getCounts();
        map.put("records",userList);
        map.put("total",count);
        return Result.success(map);
    }

    @RequestMapping("/user")
    public Result addUser(@RequestBody User user)
    {
        int res = userService.addUser(user);
        return Result.success(res);
    }

    @DeleteMapping("/user/{id}")
    public Result delUser(@PathVariable int id)
    {
        int res = userService.delUser(id);
        return Result.success(res);
    }


    @PostMapping("/updateUser")
    public Result updateUser(@RequestBody User user)
    {
        int res = userService.updateUser(user);
        return Result.success(res);
    }

}
