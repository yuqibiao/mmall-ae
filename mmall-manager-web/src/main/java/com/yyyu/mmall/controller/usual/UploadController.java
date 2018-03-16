package com.yyyu.mmall.controller.usual;

import com.yyyu.mmall.controller.BaseController;
import com.yyyu.mmall.uitls.controller.ResultUtils;
import com.yyyu.mmall.uitls.lang.StringUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * 功能：文件上传
 *
 * @author yu
 * @date 2018/3/13.
 */
@Api(value = "upload" , description = "文件上传",
        produces = MediaType.APPLICATION_JSON_VALUE)
@RequestMapping("api/upload")
@Controller
public class UploadController extends BaseController{

    @Value("${upload.savePath}")
    private String configSavePath;
    @Value("${server.address}")
    private String serverAddress;

    @ApiOperation(value = "返回服务器地址" ,httpMethod = "GET")
    @RequestMapping(value = "v1/server/address" , method = RequestMethod.GET)
    @ResponseBody
    public ResultUtils getServerAddress(){

        return ResultUtils.createSuccess("获取成功",serverAddress);
    }

    @ApiOperation(value = "图片上传" ,notes = "文件的类型仅为图片",httpMethod = "POST")
    @RequestMapping(value = "v1/img" , method = RequestMethod.POST)
    @ResponseBody
    public ResultUtils uploadImg(@ApiParam(value="文件", required=true) @RequestPart(required = true)@RequestParam("file") MultipartFile multipartFile){

        //获得图片地址
        String picName = UUID.randomUUID().toString();
        //获取文件名
        String oriName = multipartFile.getOriginalFilename();
        //获取文件后缀
        int i = oriName.lastIndexOf(".");
        String extName ="";
       if (i!=-1){
           extName = oriName.substring(i);
       }
        String uploadPath;
        if (!isPic(extName)){//不是图片
            return ResultUtils.createError("文件格式不正确，支持.jpg、.gif、.png");
        }
        System.out.println("multipartFile.getSize()"+multipartFile.getSize());
        if(multipartFile.getSize()>4*1024*1024){//文件大小限制
            return ResultUtils.createError("文件太大，最大支持上传4M的图片");
        }
        String relativeSavePath;
        if(!StringUtils.isEmpty(configSavePath)){//配置了路径
            relativeSavePath = "/img/"+ picName + extName;
            uploadPath= configSavePath + relativeSavePath;
        }else{
            relativeSavePath = "/upload/uploadFiles/img/"+ picName + extName;
            uploadPath = getProjectRealPath()+relativeSavePath;
        }
        File saveFile;
        try {
            saveFile = new File(uploadPath);
            if (!saveFile.getParentFile().exists()) {
                saveFile.getParentFile().mkdirs();
            }
            //使用流得形式
            FileUtils.copyInputStreamToFile(multipartFile.getInputStream(), saveFile);
        } catch (IOException e) {
            e.printStackTrace();
           ResultUtils.createError(e.getMessage());
        }
        ImgInfo imgInfo = new ImgInfo(serverAddress , relativeSavePath , oriName);
        return ResultUtils.createSuccess( "图片上传成功",imgInfo);
    }

    private boolean isPic(String suf) {
        List<String> sufList = new ArrayList<>();
        sufList.add(".jpg");
        sufList.add(".gif");
        sufList.add(".png");
        return isContainSuf(sufList, suf);
    }

    private boolean isContainSuf(List<String> sufList, String targetSuf) {
        for (String suf : sufList) {
            if (suf.equalsIgnoreCase(targetSuf)) {
                return true;
            }
        }
        return false;
    }

    private  static class ImgInfo{
        String serverAddress;
        String src;
        String title;

        public ImgInfo(String serverAddress, String src, String title) {
            this.serverAddress = serverAddress;
            this.src = src;
            this.title = title;
        }

        public String getServerAddress() {
            return serverAddress;
        }

        public void setServerAddress(String serverAddress) {
            this.serverAddress = serverAddress;
        }

        public String getSrc() {
            return src;
        }

        public void setSrc(String src) {
            this.src = src;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }
    }

}
