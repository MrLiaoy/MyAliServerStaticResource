package com.hand.crm.demo.controller;

import com.hand.crm.demo.service.ImgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

/**
 * @program: demo->ImgController
 * @description:
 * @author: lyl
 * @create: 2020-01-07 10:39
 **/
@RestController
public class ImgController {
    @Autowired
    ImgService imgService;

    @PostMapping("upLoadImg")
    public Map<Object, Object> upLoadImg(@RequestParam(value="imgName",required = false) String imgName, @RequestParam("img") MultipartFile img) {

        Map<Object, Object> result = imgService.upLoadImg(imgName, img);

        return result;
    }
}
