package com.hiynn.file.upload.controller;

import com.hiynn.file.upload.untils.FTPUtil;
import com.hiynn.file.upload.untils.ResultBuilder;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.ArrayList;
import java.util.UUID;

/**
 * Created by ZhouXiaoLe on 2019/7/20
 */
@RestController
@Slf4j
public class FileUploadController {
    @Value("${upload.file.path}")
    public String uploadPath;

    @PostMapping("/uploadFtp")
    public ResultBuilder uploadFtp(HttpServletRequest request, @RequestParam("file") MultipartFile file) throws IOException {
        FTPUtil ftpUtil = new FTPUtil();
        ftpUtil.uploadLocalStream("dinging/",file,file.getOriginalFilename());
        return ResultBuilder.success();
    }

    @PostMapping("/upload")
    public ResultBuilder uploadFile(HttpServletRequest request, @RequestParam("file") MultipartFile file) throws IOException {
        return ResultBuilder.success(FileUtil(request, file));
    }

    @PostMapping("/uploads")
    public ResultBuilder uploadsFile(HttpServletRequest request, @RequestParam("files") MultipartFile[] multipartFile) throws IOException {
        ArrayList<Object> list = new ArrayList<>();
        for (MultipartFile file : multipartFile) {
            list.add(FileUtil(request, file));
        }
        return ResultBuilder.success(list);
    }

    @GetMapping("/download/{fileName}")
    public ResultBuilder downloadFile(HttpServletResponse response,
                                      @PathVariable String fileName) {
        File file = new File(uploadPath + fileName);
        InputStream stream = null;
        OutputStream outputStream = null;
        try {
            fileName = new String(fileName.getBytes(), "ISO-8859-1");
            response.setCharacterEncoding("utf-8");
            response.setContentType("application/json");
            response.setHeader("Content-Disposition", "attachment;fileName=" + fileName);

            stream = new FileInputStream(file);
            outputStream = response.getOutputStream();
            byte[] bytes = new byte[1024];
            int lenth;
            while ((lenth = stream.read(bytes)) > 0) {
                outputStream.write(bytes, 0, lenth);
            }
        } catch (IOException e) {
            return ResultBuilder.fail("文件下载失败:" + e.getMessage());
        } finally {
            try {
                outputStream.close();
            } catch (IOException e) {
                return ResultBuilder.fail("文件下载失败:" + e.getMessage());
            }
            try {
                stream.close();
            } catch (IOException e) {
                return ResultBuilder.fail("文件下载失败:" + e.getMessage());
            }
        }
        return ResultBuilder.success();
    }

    public String FileUtil(HttpServletRequest request, MultipartFile file) throws IOException {
        //生成文件名
        String filePrefix = UUID.randomUUID().toString().replaceAll("-", "");
        //获取文件后缀
        String fileSuffix = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
        String filePath = String.format("%s%s%s", uploadPath, filePrefix, fileSuffix);
        FileUtils.copyInputStreamToFile(file.getInputStream(), new File(filePath));
//		file.transferTo(new File("C:\\var\\log\\1.txt"));
        String basePath = String.format("%s%s%s%s%s%s%s",
                request.getScheme(), "://",
                request.getServerName(), ":",
                request.getServerPort(),
                request.getContextPath(), "/");
        return String.format("%s%s%s%s", basePath, "image/", filePrefix, fileSuffix);
    }

}
