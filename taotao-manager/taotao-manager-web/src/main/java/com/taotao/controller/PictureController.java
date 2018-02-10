package com.taotao.controller;

import com.taotao.common.utils.JsonUtils;
import com.taotao.service.PictureUploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

@Controller
public class PictureController {

    @Autowired
    private PictureUploadService pictureUploadService;

    @ResponseBody
    @RequestMapping("/pic/upload")
    public String pictureUpload(MultipartFile uploadFile) {
        Map<String, Object> resultMap = pictureUploadService.uploadPicture(uploadFile);
        String result = JsonUtils.objectToJson(resultMap);
        return result;
    }
}
