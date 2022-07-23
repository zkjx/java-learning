package com.itheima.mapper;

import com.itheima.pojo.Brand;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface BrandMapper {

//    查询功能
    @Select("select * FROM tb_brand")
    @ResultMap("brandResultMap")  // 里面填写换名的id
    List<Brand> SelectAll();

//    添加功能
    @Insert("insert into tb_brand values (null,#{brandName},#{companyName},#{ordered},#{description}.#{status})")
    void add(Brand brand);

}
