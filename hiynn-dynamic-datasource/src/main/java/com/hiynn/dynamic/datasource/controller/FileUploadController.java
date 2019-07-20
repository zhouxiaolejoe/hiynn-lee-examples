package com.hiynn.dynamic.datasource.controller;

import com.hiynn.dynamic.datasource.entity.TRole;
import com.hiynn.dynamic.datasource.untils.ResultBuilder;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotEmpty;
import java.io.*;
import java.util.UUID;

/**
 * Created by ZhouXiaoLe on 2019/7/20
 */
@RestController
@Slf4j
@Api(tags = "文件上传")
public class FileUploadController {

	public static final String uploadPath = "C:\\var\\log\\";

	@PostMapping("/upload")
	public ResultBuilder handleFileUpload(@RequestParam("file") MultipartFile file) throws IOException {
		//生成文件名
		String filePrefix = UUID.randomUUID().toString().replaceAll("-", "");
		//获取文件后缀
		String fileSuffix = file.getOriginalFilename().split("\\.")[1];
		String filePath = String.format("%s%s%s%s", uploadPath, filePrefix, ".", fileSuffix);
		FileUtils.copyInputStreamToFile(file.getInputStream(),new File(filePath));
//		file.transferTo(new File("C:\\var\\log\\1.txt"));
		return  ResultBuilder.success(filePath);
	}

}
