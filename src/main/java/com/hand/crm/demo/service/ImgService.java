package com.hand.crm.demo.service;

import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

public interface ImgService {
    Map<Object,Object> upLoadImg(String imgName, MultipartFile img);
}
