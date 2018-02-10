package com.taotao.service.iml;

import com.taotao.common.utils.FtpUtil;
import com.taotao.common.utils.IDUtils;
import com.taotao.service.PictureUploadService;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Service
public class PictureUploadServiceImpl implements PictureUploadService {

    @Value("${FTP_ADDRES}")
    private String ftpAddres;
    @Value("${FTP_PORT}")
    private int ftpPort;
    @Value("${FTP_USERNAME}")
    private String ftpUsername;
    @Value("${FTP_PASSWORD}")
    private String ftpPassword;
    @Value("${FTP_BASE_PATH}")
    private String ftpBasepath;

    @Value("${IMAGE_BASE_URL}")
    private String imageBaseUrl;


    public Map uploadPicture(MultipartFile uploadFile) {
        Map<String, Object> resultMap = new HashMap(2);
        try {
            String oldName = uploadFile.getOriginalFilename();
            String newName = IDUtils.genImageName();
            newName = newName + oldName.substring(oldName.lastIndexOf("."));
            String imagePath = new DateTime().toString("/yyyy/MM/dd");
            boolean result = FtpUtil.uploadFile(ftpAddres, ftpPort, ftpUsername, ftpPassword,
                    ftpBasepath, imagePath, newName, uploadFile.getInputStream());
            if (!result) {
                resultMap.put("error", 1);
                resultMap.put("message", "文件上传失败");
                return resultMap;
            }
            resultMap.put("error", 0);
            resultMap.put("url", imageBaseUrl + imagePath + "/" + newName);
        } catch (IOException e) {
            resultMap.put("error", 1);
            resultMap.put("message", "文件上传失败发生异常");
            return resultMap;
        }
        return resultMap;
    }
}
