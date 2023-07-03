package com.kuang.service;

import cn.hutool.core.io.FileUtil;
import cn.hutool.crypto.SecureUtil;
import com.kuang.common.Constants;
import com.kuang.entity.MyFile;
import com.kuang.mapper.FileMapper;
import com.kuang.utils.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.List;
import java.util.UUID;

@Service
public class FileService {

    @Autowired
    private FileMapper fileMapper;

    public int getCount() {
        return fileMapper.getCount();
    }

    public List<MyFile> getAllFiles(int begin, int size, String fileName) {
        return fileMapper.getAllFiles(begin,size,fileName);
    }

    public int delFile(int id) {
        return fileMapper.delFile(id);
    }

    public String upload(MultipartFile uploadFile) {
        String originalFilename = uploadFile.getOriginalFilename(); //文件原始名字 test.jpg
        String type = originalFilename.substring(originalFilename.lastIndexOf(".")+1);  //文件后缀
        long size = uploadFile.getSize() / 1024; //文件大小，单位kb
        String url;


        MyFile myFile = new MyFile();  //用于保存于数据库的实体类Files
        myFile.setName(originalFilename);
        myFile.setSize(size);
        myFile.setType(type);

        InputStream inputStream = null;
        try {
            inputStream = uploadFile.getInputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }

        String md5 = SecureUtil.md5(inputStream);  //hutool下面的

        List<MyFile> dbMyFileList = fileMapper.queryByName(md5); //看一下有没有这个文件存在
        if(dbMyFileList.size() != 0){
            //数据库中已有该md5，则拷贝其url
            url = dbMyFileList.get(0) .getUrl();
            myFile.setUrl(url);
        }else{
//            String StorePath = "C:/Users/admin/Desktop/在线商城-后端/file";
            String StorePath = "C:/Users/admin/Desktop/在线商城-后端/src/main/resources/file";
            File folder = new File(StorePath);  //最终要转化为File类型
            if(!folder.exists()){
                folder.mkdir();  //新建文件夹，用于存储上传的文件
            }
            String folderPath = "C:/Users/admin/Desktop/在线商城-后端/src/main/resources/file/";   //文件存储文件夹的位置

            //将文件保存为UUID的名字，通过uuid生成url
            String uuid = UUID.randomUUID().toString().replace("-", "").toLowerCase();
            String finalFileName = uuid+"."+type;
            File targetFile = new File(folderPath + finalFileName);
            try {
                uploadFile.transferTo(targetFile);  //必须是完整的路径，路径+文件名
            } catch (IOException e) {
                e.printStackTrace();
            }
            url = "/file/"+finalFileName;
            myFile.setUrl(url);

        }
        myFile.setMd5(md5);

        fileMapper.insert(myFile);
        System.out.println("文件"+originalFilename+" "+url);
        return url;
    }


    //根据文件名下载文件
    public void download(String fileName, HttpServletResponse response){
        File file = new File(Constants.fileFolderPath+fileName);
        if(!file.exists()){
            throw new ServiceException(Constants.CODE_500,"文件不存在");
        }
        try {
            ServletOutputStream os = response.getOutputStream();
            response.addHeader("Content-Disposition","attachment;filename="+ URLEncoder.encode(fileName,"UTF-8"));
            response.setContentType("application/octet-stream");
            os.write(FileUtil.readBytes(file));
            os.flush();
            os.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
