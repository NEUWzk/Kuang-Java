package com.kuang.controller;

import com.kuang.common.Result;
import com.kuang.entity.MyFile;
import com.kuang.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

@CrossOrigin
@RestController
public class FileController {

    @Autowired
    private FileService fileService;

    @RequestMapping("/file/page")
    public Result getAllFiles(@RequestParam(required = false) int pageNum,
                              @RequestParam(required = false) int pageSize,
                              @RequestParam(required = false) String name)
    {
        int size = pageSize;
        int page =  Math.max(1,pageNum);
        int begin = (page-1)*size;
        List<MyFile> fileList = fileService.getAllFiles(begin,size,name);
        int count = fileService.getCount();
        HashMap<String, Object> map = new HashMap<>();
        map.put("count",count);
        map.put("records",fileList);
        return Result.success(map);
    }

    @DeleteMapping("/file/{id}")
    public Result delFile(@PathVariable int id)
    {
        int res = fileService.delFile(id);
        return Result.success(res);
    }

    @RequestMapping("/file/upload")
    public Result uploadFile(@RequestParam MultipartFile file)   //上传文件
    {
        String url = fileService.upload(file);
        return Result.success(url);
    }

    @RequestMapping("/test/upload")
    public String testUpload(@RequestParam MultipartFile multipartFile) throws IOException {
        String fileName = multipartFile.getOriginalFilename();  //test.jpg
        String pathName = "C:\\Users\\admin\\Desktop\\pic\\";
        String pathName1 = "C:/Users/admin/Desktop/pic/";
        File file = new File(pathName + fileName);
        File file1 = new File(pathName1 + fileName);
        multipartFile.transferTo(file1);  //目的路径
        return file.getAbsolutePath();
    }


    @GetMapping("/file/{fileName}")
    public void download(@PathVariable String fileName, HttpServletResponse response){
        fileService.download(fileName,response);
    }


}
