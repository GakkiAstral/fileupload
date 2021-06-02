package com.bjsxt.web.controller;

import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

/**
 * 处理文件上传的控制器
 */
@Controller
@RequestMapping("/file")
public class FileUploadController {

    /**
     * 处理单个文件上传
     * 在文件上传时MultipartFile参数的名称需要与页面的file组件的name相同，如果不同需要@RequestParam注解来指定
     */
    @RequestMapping("singleFile")
    public String singleFile(@RequestParam("file") MultipartFile files, String username, HttpServletRequest request){
        String fileName = UUID.randomUUID().toString()+files.getOriginalFilename().substring(files.getOriginalFilename().lastIndexOf("."));
        String realPath = request.getServletContext().getRealPath("/fileupload");
        File temp = new File(realPath,fileName);
        try {
            files.transferTo(temp);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "redirect:/page/ok";
    }

    /**
     * 处理多个文件上传
     */
    @RequestMapping("multiFile")
    public String multiFile(MultipartFile file[],String username,HttpServletRequest request){
        for(MultipartFile temp:file){
            String fileName = UUID.randomUUID().toString()+temp.getOriginalFilename().substring(temp.getOriginalFilename().lastIndexOf("."));
            File f = new File(request.getServletContext().getRealPath("/fileupload"),fileName);
            try {
                temp.transferTo(f);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return "redirect:/page/ok";
    }
    /**
     * 实现文件下载
     */
    @RequestMapping("/fileDown")
    public void fileDown(String fileName, HttpServletRequest request, HttpServletResponse response){
        //设置响应头
        response.setHeader("Content-Disposition","attachment;filename="+fileName);
        //获取绝对路径
        String realPath = request.getServletContext().getRealPath("/fileupload");

        File file = new File(realPath,fileName);

        //获取字节输出流
        try {
            ServletOutputStream outputStream = response.getOutputStream();
            //读文件,通过工具类
            outputStream.write(FileUtils.readFileToByteArray(file));
            outputStream.flush();
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
