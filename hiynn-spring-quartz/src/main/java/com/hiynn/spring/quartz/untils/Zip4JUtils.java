package com.hiynn.spring.quartz.untils;

import net.lingala.zip4j.ZipFile;
import net.lingala.zip4j.model.ZipParameters;
import net.lingala.zip4j.model.enums.AesKeyStrength;
import net.lingala.zip4j.model.enums.EncryptionMethod;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.zip.ZipException;

public class Zip4JUtils {
    /**
     * 解压加密的压缩文件
     * @param zipfile
     * @param dest
     * @param passwd
     * @throws ZipException
     */
    public static void unZip(File zipfile,String dest,String passwd) throws ZipException, net.lingala.zip4j.exception.ZipException {
        ZipFile zipFile = new ZipFile(zipfile, passwd.toCharArray());
        zipFile.extractAll(dest);
    }
    /**
    * @Description  压缩文件且加密
    * @Method zip
    * @Param src List<File> src 文件类型集合
    * @Param dest 目标位置+文件名(/var/lib/test.zip)
    * @Param is 是否压缩到文件夹
    * @Param passwd
    * @return void
    * @Author ZhouXiaoLe
    * @Date  2019-08-21  15:50:52
    **/
    public static void zip(String src, String dest, boolean is, String passwd) throws net.lingala.zip4j.exception.ZipException {
        File srcfile=new File(src);
        /**
         *  加密算法
         */
        ZipParameters zipParameters = new ZipParameters();
        zipParameters.setEncryptFiles(true);
        zipParameters.setEncryptionMethod(EncryptionMethod.AES);
        zipParameters.setAesKeyStrength(AesKeyStrength.KEY_STRENGTH_256);
        try {
            ZipFile zipfile = new ZipFile(dest, passwd.toCharArray());
            if(srcfile.isDirectory()){
                if(is){
                    zipfile.addFolder(srcfile, zipParameters);
                }else {
                    File[] listFiles = srcfile.listFiles();
                    ArrayList<File> temp=new ArrayList<>();
                    Collections.addAll(temp, listFiles);
                    zipfile.addFiles(temp, zipParameters);
                }
            }else{
                zipfile.addFile(srcfile, zipParameters);
                srcfile.delete();
            }
        } catch (net.lingala.zip4j.exception.ZipException e) {
            e.printStackTrace();
        }
    }
}

