package com.hiynn.file.upload.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;

/**
 * Created by ZhouXiaoLe on 2019/7/20
 */
@Controller
public class FileUploadController {
	@RequestMapping("/upload")
	@ResponseBody
	public String handleFileUpload(@RequestParam("file") MultipartFile file){
		if(!file.isEmpty()){
			try {
				BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(new File(file.getOriginalFilename())));
				out.write(file.getBytes());
				out.flush();
				out.close();
			}catch(FileNotFoundException e) {
				e.printStackTrace();
				return "上传失败,"+e.getMessage();
			}catch (IOException e) {
				e.printStackTrace();
				return "上传失败,"+e.getMessage();
			}

			return "上传成功";

		}else{

			return "上传失败，因为文件是空的.";

		}
	}

}
