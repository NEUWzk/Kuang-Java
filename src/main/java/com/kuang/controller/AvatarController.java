package com.kuang.controller;

import com.kuang.common.Result;
import com.kuang.entity.Avatar;
import com.kuang.service.AvatarService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;

@CrossOrigin
@RestController
public class AvatarController {


    @Resource
    private AvatarService avatarService;


    @GetMapping("/avatar/page")
    public Result selectPage(@RequestParam int pageNum,
                             @RequestParam int pageSize){
        int index = (pageNum - 1) * pageSize;
        List<Avatar> avatars = avatarService.selectPage(index, pageSize);
        int total = avatarService.getTotal();
        HashMap<String, Object> map = new HashMap<>();
        map.put("records",avatars);
        map.put("total",total);
        return Result.success(map);
    }


    @GetMapping("/avatar/{fileName}")
    public void download(@PathVariable String fileName, HttpServletResponse response){
        avatarService.download(fileName,response);
    }

}
