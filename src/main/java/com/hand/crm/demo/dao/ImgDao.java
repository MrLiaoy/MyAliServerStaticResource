package com.hand.crm.demo.dao;

import org.apache.ibatis.annotations.Param;

public interface ImgDao {
    int upLoadImg(@Param("imgName") String imgName, @Param("url") String url);
    int nameIsExist(String name);
}
