package com.hand.crm.demo.service;

import com.hand.crm.demo.dao.ImgDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @program: demo->ImgServiceImpl
 * @description:
 * @author: lyl
 * @create: 2020-01-07 10:45
 **/
@Service
public class ImgServiceImpl implements ImgService {
    @Value("${ali-server-url}")
    private String url;
    @Value("${ali-dir-name}")
    private String filePath;

    @Autowired
    private ImgDao imgDao;

    @Override
    public Map<Object, Object> upLoadImg(String imgName, MultipartFile img) {
        Map<Object, Object> result = new HashMap<>();
        String name;
        String suff = img.getOriginalFilename().substring(
                img.getOriginalFilename().lastIndexOf("."),
                img.getOriginalFilename().length());
        ;
        //判断是否上传的有效文件路径
        if (img.isEmpty()) {
            result.put("erro", "文件不能为空");
        } else {
            //判断是否单独填写图片名称，如果没有则文件名为图片名
            if (imgName == null || "".equals(imgName)) {
                name = img.getOriginalFilename();
            } else {
                name = imgName;
            }
//            创建file对象用于判断文件夹是否存在，如果不存在创建文件夹
            File dir = new File(filePath);
            if (!dir.exists()) {
                dir.mkdirs();
            }
//            保存文件对象并存储到数据库
            try {
//              存储到数据库
                if (imgDao.nameIsExist(name+suff) == 0) {
                    imgDao.upLoadImg(name+suff, url + name.hashCode() + suff);
                } else {
                    result.put("message", "您输入的图片名重复，请重新输入");
                    return result;
                }
                File dest = new File(filePath + name.hashCode()+ suff);
                img.transferTo(dest);

                result.put("success", "success");
                result.put("imgName", imgName);
                result.put("url", url + name.hashCode()+ suff);

            } catch (IOException e) {
                result.put("erro", "上传失败，请检查文件是否存在");
            }
        }
        return result;
    }
}
