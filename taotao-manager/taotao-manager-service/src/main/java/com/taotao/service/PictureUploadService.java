package com.taotao.service;

import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

public interface PictureUploadService {

    Map uploadPicture(MultipartFile file);
}
